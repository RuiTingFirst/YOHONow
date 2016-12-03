package lanou.dllo.yohonow.tools.timetools;

import java.text.SimpleDateFormat;

/**
 * Created by dllo on 16/12/2.
 */

public class StringTime {
    public static final String IntoTime(String time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String timenew = simpleDateFormat.format(Long.valueOf(time));
        return timenew;
    }
}
