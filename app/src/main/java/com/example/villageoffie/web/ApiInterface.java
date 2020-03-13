package com.example.villageoffie.web;

import androidx.cardview.widget.CardView;

import com.example.villageoffie.pojo.Permitpojo;
import com.example.villageoffie.pojo.TradePojo;
import com.example.villageoffie.pojo.Viewbirthpojo;
import com.example.villageoffie.pojo.Viewdeathpojo;
import com.example.villageoffie.pojo.Viewmrgpojo;
import com.example.villageoffie.pojo.login;
import com.example.villageoffie.pojo.reg;
import com.example.villageoffie.pojo.spinnerresponse;
import com.example.villageoffie.pojo.viewAppli;
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
    @POST("civil.php")
    Call<reg>register(@Field("key") String key, @Field("name") String name,@Field("mobile") String mobile,@Field("address") String address, @Field("age") String age,
                    @Field("job") String job,
                     @Field("username") String username,@Field("password") String password,@Field("image") String img
    ,@Field("cno") String cno,@Field("cvv") String cvv,@Field("fname") String f,@Field("mname") String m);

    @GET("civil.php")
    Call<login>getlogin(@Query("key") String key,@Query("username") String username,@Query("password") String password);


    @FormUrlEncoded
    @POST("civil.php")
    Call<reg>addvillage(@Field("key") String key,@Field("vname") String vname,@Field("vtaluk") String vtaluk,@Field("vdistrict") String vdistrict, @Field("vstate") String vstate,
    @Field("vplace") String vplace,@Field("vpin") String pin,@Field("vmobile") String mob,
    @Field("vusername") String vusername,@Field("vpassword") String vpassword);


    @FormUrlEncoded
    @POST("civil.php")
    Call<reg>addcertificate(@Field("key") String key,@Field("cname") String cname,@Field("creq") String creq,@Field("cfee") String cfee);

@GET("civil.php")
    Call<List<viewvillage>>villageview(@Query("key") String key);

    @GET("civil.php")
    Call<List<viewcertificate>>certificateview(@Query("key") String key);


    @GET("civil.php")
    Call<reg>certificatedelete(@Query("key") String key,@Query("cid") String id);

@GET("civil.php")
Call<reg>certficateedit(@Query("key") String key,@Query("cid") String id,@Query("cname") String name,@Query("creq") String req,@Query("cfee") String fee);

    @GET("civil.php")
    Call<reg>villagedelete(@Query("key") String key,@Query("vid") String id);

    @FormUrlEncoded
    @POST("civil.php")
    Call<reg>villageedit(@Field("key") String key,@Field("vid") String id,@Field("vname") String name,@Field("vtaluk") String taluk,@Field("vdistrict") String dis
            ,@Field("vstate") String state,@Field("vplace") String place,@Field("vpin") String pin,@Field("vmob") String mob);


    @FormUrlEncoded
    @POST("civil.php")
    Call<reg>upload(@Field("key") String key,@Field("uid") String id,@Field("dname") String name,@Field("doc") String doc);

    @GET("civil.php")
    Call<viewuser>userview(@Query("key") String key,@Query("userid") String id);
    @FormUrlEncoded
    @POST("civil.php")
    Call<login>approvetax(@Field("key") String key,@Field("pid") String uid);


    @FormUrlEncoded
    @POST("civil.php")
    Call<login>checkuserApproval(@Field("key") String key,@Field("cat") String uid,@Field("appid") String cid,@Field("opinion") String op);

    @FormUrlEncoded
    @POST("civil.php")
    Call<reg>applicatin(@Field("key") String key,@Field("uid")String uid,
                 @Field("name") String cid,@Field("sex") String name,
                 @Field("dob") String sslip,@Field("btime") String date
            ,@Field("place") String fee,@Field("dor") String dor,@Field("mname") String mname,
                 @Field("fname") String fname,@Field("address") String addr);

    @FormUrlEncoded
    @POST("civil.php")
    Call<reg>applicatin1(@Field("key") String key,@Field("uid")String uid,
                        @Field("name") String cid,@Field("sex") String name,
                        @Field("dob") String sslip,@Field("btime") String date
            ,@Field("place") String fee,@Field("dor") String dor,@Field("mname") String mname,
                        @Field("fname") String fname,@Field("address") String addr,@Field("dtime") String dtime,
                         @Field("dreason") String re);

    @FormUrlEncoded
    @POST("civil.php")
    Call<reg>applicatin2(@Field("key") String key,@Field("uid")String uid,
                         @Field("name") String cid,@Field("sex") String name,
                         @Field("dob") String sslip,@Field("btime") String date
            ,@Field("place") String fee,@Field("dor") String dor,@Field("mname") String mname,
                         @Field("fname") String fname,@Field("nationality") String addr,@Field("job") String dtime,
                         @Field("address") String re,
                         @Field("wname") String wname,@Field("wsex") String wsex,@Field("wdob") String wdob,
                         @Field("wmname") String wmname, @Field("wfname") String wfname,@Field("wnationality") String wnnaddr,@Field("wjob") String dwtime,
                         @Field("waddress") String wre,@Field("hpic") String whpicre,@Field("wpic") String wpic);


    @FormUrlEncoded
    @POST("civil.php")
    Call<reg>applicatin3(@Field("key") String key,@Field("uid")String uid,
                        @Field("oname") String cid,@Field("nrooms") String name,
                        @Field("sqft") String sslip,@Field("address") String date,@Field("adate") String date2);
    @FormUrlEncoded
    @POST("civil.php")
    Call<reg>applicatin4(@Field("key") String key,@Field("uid")String uid,@Field("sname")String uid1,
                         @Field("spur") String cid,@Field("sowner") String name,
                         @Field("swno") String sslip,@Field("sbno") String date,@Field("saddress") String dat2e,@Field("adate") String date2);

    @FormUrlEncoded
    @POST("civil.php")
    Call<login> paytax(@Field("key") String token,@Field("uid") String type,
                            @Field("id") String userid1, @Field("type") String date,
                       @Field("amount") String amt, @Field("date") String dd);

    @FormUrlEncoded
    @POST("civil.php")
    Call<login> getfeedback(@Field("key") String token,@Field("rname") String type,
                                  @Field("rfeed") String userid1, @Field("date") String date);
    @GET("civil.php")
    Call<List<viewuserapplication>>appview(@Query("key") String key);

    @GET("civil.php")
    Call<List<viewdocument>>docs(@Query("key") String key,@Query("userid") String uid);
    @GET("civil.php")
    Call<List<spinnerresponse>>spinner(@Query("key") String key);
    @GET("civil.php")
    Call<viewAppli>getAppli(@Query("key") String key);
    @GET("civil.php")
    Call<Viewbirthpojo>getBirth(@Query("key") String key, @Query("uid") String uid, @Query("date") String date);
    @GET("civil.php")
    Call<Viewdeathpojo>getDeath(@Query("key") String key, @Query("uid") String uid, @Query("date") String date);
    @GET("civil.php")
    Call<Viewmrgpojo>getmrg(@Query("key") String key, @Query("uid") String uid, @Query("date") String date);

    @GET("civil.php")
    Call<Permitpojo>getpermit(@Query("key") String key, @Query("uid") String uid, @Query("date") String date);
    @GET("civil.php")
    Call<TradePojo>getTrade(@Query("key") String key, @Query("uid") String uid, @Query("date") String date);
    @GET("civil.php")
    Call<List<Permitpojo>>ViewMyBuild(@Query("key") String key,@Query("uid") String uid);
    @GET("civil.php")
    Call<List<TradePojo>>gettrade(@Query("key") String key,@Query("uid") String uid);

    @GET("civil.php")
    Call<List<Permitpojo>>viewbtax(@Query("key") String key,@Query("type") String type);
    @GET("civil.php")
    Call<List<TradePojo>>Viewptax(@Query("key") String key,@Query("type") String type);
}



