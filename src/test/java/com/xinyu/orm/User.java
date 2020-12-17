package com.xinyu.orm;

import java.util.Date;

/**   
 * @Title:User
 * @author:彭嘉
 * @date:2020年10月26日 下午7:02:55  
 * @Description:用户   
 */
public class User {

    private String id;// id
    private String name;// 姓名
    private int age;// 年龄
    private Date birthday;// 生日

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User(String id, String name, int age, Date birthday) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public User() {
        super();
    }

}



