package lanou.dllo.yohonow.tools.volleytools;

/**
 * Created by dllo on 16/11/29.
 */

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

/**
 * 这个请求可以直接将数据进行解析, 所以可以直接使用请求后的数据
 */
public class GsonRequest<T> extends Request<T>{
    public GsonRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        return null;
    }

    @Override
    protected void deliverResponse(T response) {

    }
}
