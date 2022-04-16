package com.example.camerakt

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.camerakt.databinding.ActivityMainBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    val REQUEST_IMAGE_CAPTURE = 1 // 카메라 사진촬영 요청코드
    lateinit var curPhotoPath: String // 문자열 형태의 사진 경로 값 ( 초기 값을 null로 시작하고 싶을 때)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setPermission() // 권한을 체크하는 메소드 수행.

        binding.btnCamera.setOnClickListener {
            takeCapture() // 기본 카메라 앱을 실행하여 사진 촬영
        }
    }

    /*
        카메라 촬영
    */
    private fun takeCapture() {
        // 기본 카메라 앱 실행
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFIle: File? = try {
                    createImageFile()
                } catch (ex: IOException){
                    null
                }
                photoFIle.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.camerakt.fileprovider",
                        it!!
                    )
                    val intent = Intent(this, ActivityMainBinding::class.java)
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    /*
                        startActivity 앱을 다른 엑티비티로 이동을 할 때 사용하는데 ForResult를 붙히게 되면
                        엑티비티가 메인 엑티비티에서 서브 엑티비티로 이동했다가 서브 엑티비티로 부터 메인으로 돌아오면서
                        받게되는 결과값을 메인 엑티비티에서 받을 수 있다.
                        왜 startActivity로 하지않고 startActivityForResult를 붙혀서 하는지?
                        intent로 실행하는 앱이 카메라 기본앱을 실행하는데 카메라 앱 자체도 Activity형태이다.
                        엑티비티에서 사진을 찍고나서의 결과값을 ActivityForResult 통해서 받아낸다.
                        기본 카메라앱을 찍고 거기서 카메라 앱에 대해 얻은 사진 결과물을 다시 가져와야 하기 때문에
                        startActivityForResult를 사용해서 갔다 온다라는 뜻이 된다.
                        -> 현재 deprecated가 되었다.....
                     */
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    /*
        이미지 파일 생성
    */
    private fun createImageFile(): File {
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) // 날짜를 표기할 때
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES) // ? = nullable
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", storageDir)
            .apply { curPhotoPath = absolutePath }
    }
    /*
        테드 퍼미션 설정
    */
    private fun setPermission() {
        val permission = object : PermissionListener {
            override fun onPermissionGranted() { // 설정해놓은 위험권한들이 허용되었을 경우 이곳을 수행함.
                Toast.makeText(this@MainActivity, "권한이 허용 되었습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) { // 설정해놓은 위험권한들중 거부를 한 경우 이곳을 수행함.
                Toast.makeText(this@MainActivity, "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show()
            }

        }

        TedPermission.with(this)
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 앱를 사용하시려면 권한을 허용해주세요.")
            .setDeniedMessage("권한을 거부하셨습니다. [앱 설정] -> [권한] 항목에서 허용해주세요")
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA)
            .check()
    }

    // startActivityForResult를 통해서 기본 카메라 앱으로 부터 받아온 사진 결과값
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 이미지를 성공적으로 가져왔다면?
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val bitmap: Bitmap
            val file = File(curPhotoPath)
            if (Build.VERSION.SDK_INT < 28 ) { // 안드로이드 9.0(Pie) 버전보다 낮을 경우
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(file))
                binding.ivProfile.setImageBitmap(bitmap)
            } else { // 안드로이드 9.0(Pie) 버전보다 높을 경우
                val decode = ImageDecoder.createSource(
                    this.contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                binding.ivProfile.setImageBitmap(bitmap)
            }
            savePhoto(bitmap)
        }
    }
    /*
        갤러리에 저장
     */

    private fun savePhoto(bitmap: Bitmap) {
        val folderPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/" // 사진 폴더로 저장하기 위한 경로 선언
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val fileName = "${timestamp}.jpeg"
        val folder = File(folderPath)
        if(!folder.isDirectory){ // 현재 해당 경로에 폴더가 존재하는지 검사 -> ! 존재하지 않는다면?
            folder.mkdirs() // make directory 줄임말로 해당 경로에 폴더 자동으로 새로 만들기
        }

        // 실제적인 저장처리
        val out = FileOutputStream(folderPath + fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        Toast.makeText(this, "사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show()
    }
}