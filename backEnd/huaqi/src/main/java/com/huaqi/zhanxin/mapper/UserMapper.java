package com.huaqi.zhanxin.mapper;

import com.huaqi.zhanxin.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from User")
    List<UserBean> userList();

    @Select("select * from User where user_email=#{userEmail}")
    UserBean loginUser(@Param("userEmail") String userEmail);

    @Select("select * from User where user_id=#{userID}")
    UserBean selectName(@Param("userID") int userID);

    @Select("select * from Identity_Info where user_id=#{userID}")
    UserInfo getInfo(@Param("userID") int userID);

    @Update("update User set user_name = #{userName},user_email=#{userEmail} where user_id = #{userID}")
    int updateName(@Param("userID") int userID,@Param("userName") String userName,@Param("userEmail") String userEmail);

    @Update("update Identity_Info set occupation = #{occupation},annual_income = #{annualIncome},working_years = #{workingYears},phone=#{phone} where user_id = #{userID}")
    int updateInfo(@Param("userID") int userID,@Param("occupation") int occupation,@Param("annualIncome") float annualIncome,@Param("workingYears") int workingYears,@Param("phone") String phone);

    @Insert("insert into Identity_Info(occupation,annual_income,working_years,user_id,phone) values (#{occupation},#{annualIncome},#{workingYears},#{userID},#{phone})")
    int insertInfo(@Param("occupation") int occupation,@Param("annualIncome") float annualIncome,@Param("workingYears") int workingYears,@Param("userID") int userID,@Param("phone") String phone);

    @Insert("insert into Identity_Info(user_id) values (#{userID})")
    int insertNewInfo(@Param("userID") int userID);

    // 注册向数据库插入数据
    @Insert("insert into User(user_email,user_pwd,user_type,user_register_time) values (#{userEmail},#{userPwd},#{userType},#{userRegisterTime})")
    int registerUser(@Param("userEmail") String userEmail,@Param("userPwd") String userPwd,@Param("userType") int userType,@Param("userRegisterTime") LocalDateTime userRegisterTime);

    //实名
    @Update("update Identity_Info set authentication = #{authentication},IDtype = #{IDtype},IDcard = #{IDcard} where user_id = #{userID}")
    int updateAuthentication(@Param("userID") int userID,@Param("authentication") boolean authentication,@Param("IDtype") int IDtype,@Param("IDcard") String IDcard);

    // 查询数据库中信誉证明信息
    @Select("select * from Reputation where user_id=#{userID}")
    HonestyProof selectHonestyProof(@Param("userID") int userID);

    // 插入新用户数据
    @Insert("insert into Reputation(user_id) values (#{userID})")
    int insertNewReputation(@Param("userID") int userID);

    // 查询数据库中资产证明信息
    @Select("select * from Asset where user_id=#{userID}")
    Asset selectAsset(@Param("userID") int userID);

    // 查询数据库中信贷记录信息
    @Select("select * from Credit_Record where user_id=#{userID}")
    CreditRecord selectCreditRecord(@Param("userID") int userID);
    // 插入新用户数据
    @Insert("insert into Credit_Record values (#{userID},#{debtRatio},#{numberRealEstateLoansOrLines},#{numberOfOpenCreditLinesAndLoans},#{numberOfTime30To59DaysPastDueNotWorse},#{revolvingUtilizationOfUnsecuredLines},#{seriousDlqin2yrs},#{monthlyIncome},#{numberOfTime90DaysLate},#{numberOfDependents},#{numberOfTime60To89DaysPastDueNotWorse})")
    int insertCreditRecord(@Param("userID") int userID,@Param("debtRatio") double debtRatio,@Param("numberRealEstateLoansOrLines") int numberRealEstateLoansOrLines,@Param("numberOfOpenCreditLinesAndLoans") int numberOfOpenCreditLinesAndLoans,@Param("numberOfTime30To59DaysPastDueNotWorse") int numberOfTime30To59DaysPastDueNotWorse,@Param("revolvingUtilizationOfUnsecuredLines") double revolvingUtilizationOfUnsecuredLines,@Param("seriousDlqin2yrs") int seriousDlqin2yrs,@Param("monthlyIncome") double monthlyIncome,@Param("numberOfTime90DaysLate") int numberOfTime90DaysLate,@Param("numberOfDependents") int numberOfDependents,@Param("numberOfTime60To89DaysPastDueNotWorse") int numberOfTime60To89DaysPastDueNotWorse);

    // 修改密码
    @Update("update User set user_pwd = #{userPwd} where user_email=#{userEmail}")
    int updatePwd(@Param("userPwd") String userPwd,@Param("userEmail") String userEmail);

    @Update("update User set user_pwd = #{userPwd} where user_id=#{userID}")
    int changePwd(@Param("userPwd") String userPwd,@Param("userID") int userID);

    // 上传头像
    @Update("update User set user_avatar = #{userAvatar} where user_id=#{userID}")
    String updateAvatar(@Param("userAvatar") String userAvatar, @Param("userID") int userID);

    // 登录异常
    @Insert("insert into Login_Exception values(#{userID},#{exceptionTime})")
    int insertException(@Param("userID") int userID, @Param("exceptionTime") Timestamp exceptionTime);

    // 安全问题
    @Insert("insert into Security_Question values(#{userID},#{teacher},#{city})")
    int insertSecurityQuestion(@Param("userID") int userID, @Param("teacher") String teacher, @Param("city") String city);

    @Select("select * from Security_Question where user_id=#{userID}")
    SecurityQuestion selectSecurityQuestion(@Param("userID") int userID);

    //获取年龄和分数（年龄由身份证号得到），左外连接
    @Select(("select Score.user_id, total_score, IDcard from Identity_Info natural right outer join Score"))
    List<AgeScoreBean> selectAllAges();

    // 向用户信用分数历史表中插入数据（同步于注册）
    @Insert("insert into History_Score values (#{userID}, #{totalScore}, #{historyTime}, #{identityScore}, " +
            "#{assetScore}, #{creditScore}, #{behaviourScore}, #{socialScore})")
    int insertHistoryRecord(@Param("userID") int userID, @Param("totalScore") int totalScore,
                            @Param("historyTime") Timestamp historyTime, @Param("identityScore") int identityScore,
                            @Param("assetScore") int assetScore, @Param("creditScore") int creditScore,
                            @Param("behaviourScore") int behaviourScore, @Param("socialScore") int socialScore);

    // 向用户信用分数历史表中更新数据（一月一更或一周一更）
    @Update("update History_Score set total_score = #{historyScore}, history_time = #{historyTime}, " +
            "identity_score = #{identityScore}, asset_score = #{assetsScore}, credit_score = #{creditScore}, " +
            "behavior_score = #{behaviourScore}, social_score = #{socialScore} where user_id = #{userID}")
    int changeRecord(@Param("userID") int userID, @Param("historyScore") int historyScore,
                     @Param("historyTime") Timestamp historyTime, @Param("identityScore") int identityScore,
                     @Param("assetsScore") int assetsScore, @Param("creditScore") int creditScore,
                     @Param("behaviourScore") int behaviourScore, @Param("socialScore") int socialScore);

    //获取目标用户的信用分数
    @Select("select * from History_Score where user_id = #{userId}")
    HistoryScore selectUserCredit(@Param("userId") int userId);

    // 获取user_id的list
    @Select("select user_id from History_Score")
    List<Integer> selectUserIdList();
}
