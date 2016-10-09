package com.example.maqingwei.rongyunchat.entity;

/**
 * Created by maqingwei
 * Date on 16/10/9 下午12:04
 *
 * @Description:
 */
public class FriendsList {


    /**
     * id : 10010
     * userName : 中国联通
     * email : 10010@qq.com
     * portrait : http://img4.imgtn.bdimg.com/it/u=811523984,4099066416&fm=11&gp=0.jpg
     * status : 0
     */

    private String id;
    private String userName;
    private String email;
    private String portrait;
    private String status;


    public FriendsList(String email, String userName, String portrait, String status, String id) {
        this.email = email;
        this.userName = userName;
        this.portrait = portrait;
        this.status = status;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
