package org.software.meister.gsm.retrofit2example

import org.software.meister.gsm.retrofit2example.model.MealResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("mealServiceDietInfo")                 // Get 요청을 mealServiceDietInfo 라는 url을 base url에 붙여서 전송
    fun getMeal(
        @Query("KEY") key : String = "67ebdc05dfea4d1dbc9fe76fc5a7932b",
        @Query("Type") type : String = "json",
        @Query("pIndex") index : Int = 1,
        @Query("pSize") size : Int = 5,
        @Query("ATPT_OFCDC_SC_CODE") local_code : String = "F10",
        @Query("SD_SCHUL_CODE") school_code : String = "7380292",
        @Query("MLSV_YMD") ymd : String
    ) : Call<MealResponse>                          // 리턴 타입 설정
}





/*
    @Query -> ("") 에서 "" 안에 있는 단어를 처음은 ? 뒤에, 이후로는 &를 붙이고, 키워드=전송값 형식으로 url에 붙인다
    그렇기에 현재 위의 요청을 그대로 보내면
    https://open.neis.go.kr/hub/mealServiceDietInfo?KEY=67ebdc05dfea4d1dbc9fe76fc5a7932b&Type=json&pIndex=1&pSize=5&ATPT_OFCDC_SC_CODE=F10&SD_SCHUL_CODE=7380292&MLSV_YMD=20211012
    이렇게 된다

    @Path -> 요청 url에 {} 또는 <> 이 들어가는 경우가 생기는데 이 안의 문자열
    예) -> {id} 라면 ("")의 ""안에 id를 넣고 값을 넣어주면 {id} 부분이 넘겨준 값으로 대체 된다

    @Field -> 대부분 POST 요청에 사용되며 이때 @POST 위에 @FormUrlEncoded 를 붙여준다
    api 요청값을 객체가 아닌 하나하나 별개로 보내준다.

    @Header -> 대부분 token 값을 보내기 위해 사용된다. 형식은 @Header("Authorization") token : String

    @Body -> api 요청값을 객체로 넘겨준다. 이 객체에서 요청값도 SerializedName과 변수명을 통해서 이름을 지정한다.
    형식은 @Body requestBody : RequestBody

    @Part("gender") gender: RequestBody,         사진, 파일과 같이 다른 요청값을 보낼때 사용한다.
    @Part image: MultipartBody.Part     사진, 파일을 보내는 요청 값이다.

    val imageFileUrl = Uri.fromFile(file).toString()
    val genderBody: RequestBody = value.toRequestBody("multipart/form-data".toMediaTypeOrNull())     value를 RequestBody를 통해 요청값으로 전달해주는 것이다.
    val mimeType: String = URLConnection.guessContentTypeFromName(imageFileUrl)                     파일의 url을 접속한다 (로컬 파일이기 때문에 주로 경로가 전달된다)
        val fileReqBody = file.asRequestBody(mimeType.toMediaTypeOrNull())                          파일을 RequestBody로 만든다
        val imageFile = MultipartBody.Part.createFormData("image", "file1.jpeg", fileReqBody)       파일을 MultipartBody.Part 데이터로 만들어 서버에 전송한다
        
 */