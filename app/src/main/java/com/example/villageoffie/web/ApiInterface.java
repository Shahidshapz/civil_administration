package com.example.villageoffie.web;

import androidx.cardview.widget.CardView;

import com.example.villageoffie.pojo.login;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.pojo.viewcertificate;
import com.example.villageoffie.pojo.viewdocument;
import com.example.villageoffie.pojo.viewuser;
import com.example.villageoffie.pojo.viewuserapplication;
import com.example.villageoffie.pojo.viewvillage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("village.php")
    Call<reg>register(@Field("key") String key, @Field("name") String name,@Field("mobile") String mobile,@Field("address") String address, @Field("age") String age,
                     @Field("village") String village,@Field("taluk") String taluk,@Field("district") String district,@Field("job") String job,
                     @Field("username") String username,@Field("password") String password,@Field("image") String img);

    @GET("village.php")
    Call<login>getlogin(@Query("key") String key,@Query("username") String username,@Query("password") String password);


    @FormUrlEncoded
    @POST("village.php")
    Call<reg>addvillage(@Field("key") String key,@Field("vname") String vname,@Field("vtaluk") String vtaluk,@Field("vdistrict") String vdistrict, @Field("vstate") String vstate,
    @Field("vplace") String vplace,@Field("vpin") String pin,@Field("vmobile") String mob,
    @Field("vusername") String vusername,@Field("vpassword") String vpassword);


    @FormUrlEncoded
    @POST("village.php")
    Call<reg>addcertificate(@Field("key") String key,@Field("cname") String cname,@Field("creq") String creq,@Field("cfee") String cfee);

@GET("village.php")
    Call<List<viewvillage>>villageview(@Query("key") String key);

    @GET("village.php")
    Call<List<viewcertificate>>certificateview(@Query("key") String key);


    @GET("village.php")
    Call<reg>certificatedelete(@Query("key") String key,@Query("cid") String id);

@GET("village.php")
Call<reg>certficateedit(@Query("key") String key,@Query("cid") String id,@Query("cname") String name,@Query("creq") String req,@Query("cfee") String fee);

    @GET("village.php")
    Call<reg>villagedelete(@Query("key") String key,@Query("vid") String id);

    @FormUrlEncoded
    @POST("village.php")
    Call<reg>villageedit(@Field("key") String key,@Field("vid") String id,@Field("vname") String name,@Field("vtaluk") String taluk,@Field("vdistrict") String dis
            ,@Field("vstate") String state,@Field("vplace") String place,@Field("vpin") String pin,@Field("vmob") String mob);


    @FormUrlEncoded
    @POST("village.php")
    Call<reg>upload(@Field("key") String key,@Field("uid") String id,@Field("dname") String name,@Field("doc") String doc);

    @GET("village.php")
    Call<viewuser>userview(@Query("key") String key,@Query("userid") String id);

    @FormUrlEncoded
    @POST("village.php")
    Call<reg>app(@Field("key") String key,@Field("uid") String uid,@Field("cid") String cid,@Field("cname") String name);

    @GET("village.php")
    Call<List<viewuserapplication>>appview(@Query("key") String key,@Query("userid") String id);

    @GET("village.php")
    Call<List<viewdocument>>docs(@Query("key") String key,@Query("userid") String uid);

}



