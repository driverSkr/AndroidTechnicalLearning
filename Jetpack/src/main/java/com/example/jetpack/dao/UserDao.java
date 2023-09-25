package com.example.jetpack.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.jetpack.entity.User;

import java.util.List;

/*Dao 是数据访问对象的意思，通常会在这里对数据库的各项操作进行封装*/
/*所有访问数据库的操作都是在这里封装的*/
//访问数据库的操作无非就是增删改查这4种，但是业务需求却是千变万化的。而Dao 要做的事情就是覆盖所有的业务需求，使得业务方永远只需要与Dao层进行交互，而不必和底层的数据库打交道
@Dao
public interface UserDao {

   //将自动生成的主键id值返回
   @Insert
   Long insertUser(User user);

   @Update
   void updateUser(User newUser);

   @Query("select * from User")
   List<User> loadAllUsers();

   @Query("select * from User where age > :age")
   List<User> loadUsersOlderThan(int age);

   @Delete
   void deleteUser(User user);

   //如果是使用非实体类参数来增删改数据，那么也要编写SQL语句才行，而且这个时
   //候不能使用@Insert、@Delete或@Update注解，而是都要使用@Query注解才行
   @Query("delete from User where lastName = :lastName")
   int deleteUserByLastName(String lastName);
}
