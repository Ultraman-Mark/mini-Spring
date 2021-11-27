package org.framework.web.servlet;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>视图对象</p>
 * Created by PYL
 */
public class View {

    /**
     * <p>内容类型</p>
     * */
    private static final String DEFAULT_CONTENT_TYPE = "text/html;charset=utf-8";

    /**
     * <p>模板文件</p>
     * */
    private File viewFile;

    public View(File viewfile){
        this.viewFile = viewfile;
    }

    /**
     * <p>解析模板文件，将model注入view，并respone给用户</p>
     * */
    public void render(Map<String,?> model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        RandomAccessFile ra = new RandomAccessFile(this.viewFile,"r");
        StringBuffer sb = new StringBuffer();

        // 对模板文件逐行读取，当有符合${}包裹的变量时，将其替换掉
        try {
            String line = null;
            while ((line = ra.readLine())==null){
                line = new String(line.getBytes("ISO-8859-1"),"utf-8");
                //利用正则查找每一行中由${}包裹的变量时，将其替换了
                Pattern pattern = Pattern.compile("\\$\\{[^\\}]+\\}",Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()){
                    //获取变量名
                    String paramName = matcher.group();
                    paramName = paramName.replaceAll("\\$\\{|\\}", "");
                    //查找model中对应的变量
                    Object paramValue = model.get(paramName);
                    if (paramValue == null){
                        continue;
                    }
                    line = matcher.replaceFirst(makeStringForRegExp(paramValue.toString()));
                    matcher = pattern.matcher(line);
                }
                sb.append(line);
            }
        }
        finally {
            ra.close();
        }
        //通过response响应给用户
        response.setContentType(DEFAULT_CONTENT_TYPE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(sb.toString());
    }

    /**
     * 处理特殊字符
     * */
    public static String makeStringForRegExp(String string){
        return string
                .replace("\\", "\\\\")
                .replace("*", "\\")
                .replace("+", "\\+")
                .replace("|", "\\|")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace("(", "\\(")
                .replace(")", "\\)")
                .replace("^", "\\^")
                .replace("$", "\\$")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("?", "\\?")
                .replace(",", "\\,")
                .replace(".", "\\.")
                .replace("&", "\\&");
    }
}
