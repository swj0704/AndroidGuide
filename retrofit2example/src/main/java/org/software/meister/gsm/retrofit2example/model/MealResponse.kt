package org.software.meister.gsm.retrofit2example.model

import com.google.gson.annotations.SerializedName

// 응답 객체 생성

data class MealResponse(
    @SerializedName("mealServiceDietInfo")                  //응답 객체의 이름
    val mealServiceDietInfo : ArrayList<MealServiceDietInfo>
)

data class MealServiceDietInfo(
    val row : ArrayList<Row>? = null                              //응답 객체의 이름은 serialized name의 "" 안 또는
                                                                  //실제 객체의 변수명으로 적어준다.
)

data class Row(
    val ATPT_OFCDC_SC_CODE : String,
    val ATPT_OFCDC_SC_NM : String,
    val SD_SCHUL_CODE : String,
    val SCHUL_NM : String,
    val MMEAL_SC_CODE : String,
    val MMEAL_SC_NM : String,
    val MLSV_YMD : String,
    val MLSV_FGR : String,
    val DDISH_NM : String,
    val ORPLC_INFO : String,
    val CAL_INFO : String,
    val NTR_INFO : String,
    val MLSV_FROM_YMD : String,
    val MLSV_TO_YMD : String
)