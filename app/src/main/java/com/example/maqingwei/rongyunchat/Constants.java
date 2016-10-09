package com.example.maqingwei.rongyunchat;

/**
 * Created by maqingwei
 * Date on 16/9/28 下午2:54
 *
 * @Description:
 */
public class Constants {

    private final static String IP = "http://192.168.1.104:8080/";

    public final static String TOKEN_URL = IP + "springmvc/restWebservice/getToken.do?";

    public final static String CHATROOM_URL = IP + "springmvc/restWebservice/chatroom.do?param=[{\"id\":\"1001\",\"name\":\"ww\"}]";

    public final static String ADD_FRIENDS = IP + "springmvc/restWebservice/search.do?";

    //头像地址
    public final static String USER_IMG = "http://2.pic.pc6.com/up/2015-1/2015111193414.png";

    public final static String USER_PHOTO = "http://3.pic.pc6.com/thumb/up/2016-7/20167281157105426425_80_80.jpg";

    public final static String CHATROOM_INFO = IP + "springmvc/restWebservice/chatroom.do?name=";
}
