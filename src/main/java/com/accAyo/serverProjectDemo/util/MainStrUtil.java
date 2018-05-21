package com.accAyo.serverProjectDemo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: shixiangyu
 * @Description:
 * @Date: create in 下午11:57 2018/5/20
 */
public class MainStrUtil {

    // ASCII 12288
    public static char SPACE_CN = '　';
    // ASCII
    public static char SPACE_EN = ' ';
    private static Pattern ptnEmotion = Pattern
            .compile("\\[[\\u4e00-\\u9fa5|\\w]*\\]");

    private static Pattern ptnAt = Pattern
            .compile("@([^：；！￥……（）【】、？。，《》@\\s\\p{Punct}[_]]+)");
    /**
     * @param args
     */
    public static String encodeUtf8(String name) {
        try {
            name = new String(name.getBytes("iso8859-1"), "utf-8");
            return name;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "[" + name + "]";
        }
    }

    /**
     * 当方法中出现 return "/tag/book/{中文}" 的时候 {中文}需要调用此方法处理
     *
     * @param name
     * @return
     */
    public static String encode88591(String name) {
        try {
            name = new String(name.getBytes("utf-8"), "iso8859-1");
            return name;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "[" + name + "]";
        }
    }

    /**
     * ascii 英文空格32 ,中文空格12288 换行10 回车13 ,一般是\r\n 1处理html代码(这个要先做) 2将换行替换成<br/>
     * 3处理开始有回车的情况
     */
    public static String dealContent(String str) {
        if (str == null)
            return null;
        // str = str.replaceAll("<br/>", "\n");
        // str = str.replaceAll("<br />", "\n");
        char[] c = str.toCharArray();
        int j = 0;
        char k1 = '　';
        StringBuffer sb = new StringBuffer();

        boolean addBr = false;
        for (int i = 0; i < c.length; i++) {
            char t = c[i];
            if (t == 10)
                addBr = true;
            if (addBr == true && (t == 32 || t == 12288 || t == 10 || t == 13))
                continue;
            else {
                if (addBr == true) {
                    sb.append((char) (13));
                    sb.append((char) (10));
                    sb.append(k1);
                    sb.append(k1);
                    sb.append(t);
                    addBr = false;
                } else {
                    sb.append(t);
                }

            }

        }
		/*
		 * int i=0; for (char n : nc) System.out.println(n + "]n==" + (n == 10)
		 * + "=r=" + (n == 13) + "==[]" + (n == 12288));
		 */

        str = sb.toString();
        str = StringUtil.escapeHtml(str);
        str = str.trim().replaceAll("\r\n", "<br/><br/>");

        return str;
    }

    public static String dealContent(String str, boolean dealHtml) {
        if (str == null)
            return null;
        if (dealHtml)
            str = StringUtil.escapeHtml(str);
        str = str.trim().replaceAll("\n\r", "<br/>");
        str = str.trim().replaceAll("\n", "<br/>");
        str = str.trim().replaceAll("\r", "<br/>");
        if (str.startsWith("<br/>"))
            str = str.replaceFirst("<br/>", "").trim();
        if (str.startsWith("　　<br/>"))
            str = str.replaceFirst("　　<br/>", "").trim();
        str = "　　" + str.replaceAll("( ){2,}", "");
        return str;
    }

    public static String getSummary(String content) {

        if (content == null)
            return null;

        String summary = content.replaceAll("[\\n\\r]", "");
        // summary = summary.replaceAll("[\\t]", "");
        summary = summary.replaceAll("[\\s\\u3000]+", " ");

        if (summary.length() <= Constants.SUMMARY_LENGTH)
            return summary;

        return summary.substring(0, Constants.SUMMARY_LENGTH) + "...";

    }

    /**
     * 未某段文字添加
     * <p>
     * 标签
     *
     * @param introduce
     * @return
     */
    public static String addP(String str) {
        if (str == null || "".equals(str.trim()))
            return str;
        String st[] = str.split("\n");
        StringBuffer sb = new StringBuffer();
        for (String s : st) {
            sb.append("<p>");
            sb.append(s);
            sb.append("</p>");
        }
        return sb.toString();
    }

    public static String addIndentation(String str) {
        if (str == null || "".equals(str.trim()))
            return str;
        String st[] = str.split("\n");
        StringBuffer sb = new StringBuffer();
        for (String s : st) {
            sb.append(s.replaceFirst("[　\\s]*", "　　"));
        }
        return sb.toString();
    }

    /**
     * 未某段文字添加
     * <p>
     * 标签
     *
     * @param introduce
     * @return
     */
    public static String addBR(String str) {
        if (str == null)
            return null;
        String st[] = str.split("\n");
        StringBuffer sb = new StringBuffer();
        for (String s : st) {
            sb.append(s);
            sb.append("<br/>");
        }
        return sb.toString();
    }

    /**
     * String st[] = str.split("\r\n");
     *
     * @param str
     * @return
     */
    public static String addBR_RN(String str) {
        if (str == null)
            return null;
        return str.replaceAll("\n", "<br/>");
    }

    public static String replaceN_RN(String str) {
        if (str == null)
            return null;
        str = str.replaceAll("\r\n", "\n");
        return str.replaceAll("\n", "\r\n");
    }

    /**
     * 过滤所有标点符号(包括半角、全角)
     *
     * @param text
     * @return
     */
    public static String filterMarks(String text) {
        if (text == null)
            return null;
        // String pat_mark =
        // "[\\s\\p{Punct}\\-,\\/,\\|,\\$,\\+,\\%,\\&,\\',\\(,\\),\\*,\\x20-\\x2f,\\x3a-\\x40,\\x5b-\\x60,\\x7b-\\x7e,\\x80-\\xff,\\u3000-\\u3002,\\u300a,\\u300b,\\u300e-\\u3011,\\u2014,\\u2018,\\u2019,\\u201c,\\u201d,\\u2026,\\u203b,\\u25ce,\\uff01-\\uff5e,\\uffe5]+";
        String pat_mark = "[\\s\\u3000]";
        return text.replaceAll(pat_mark, "");
    }

    public static String filterShortMarks(String text) {
        if (text == null)
            return null;
        // String pat_mark =
        // "[\\s\\p{Punct}\\-,\\/,\\|,\\$,\\+,\\%,\\&,\\',\\(,\\),\\*,\\x20-\\x2f,\\x3a-\\x40,\\x5b-\\x60,\\x7b-\\x7e,\\x80-\\xff,\\u3000-\\u3002,\\u300a,\\u300b,\\u300e-\\u3011,\\u2014,\\u2018,\\u2019,\\u201c,\\u201d,\\u2026,\\u203b,\\u25ce,\\uff01-\\uff5e,\\uffe5]+";
        String pat_mark = "[\\s\\u3000]";
        String pat_form = "^\\{\\{(((it|pb|al|ac|ar|i|k|q).)*(it|pb|al|ac|ar|i|k|q))?\\}\\}";
        String temp = "";
        String[] split = text.split("\n");
        for (String str : split) {
            temp = temp
                    + str.replaceAll(pat_form, "").trim()
                    .replaceAll(pat_mark, "");
        }
        return temp;
    }

    public static String getEmotionUrl(String emotion) {
        if (emotion == null)
            return null;
        return ConfigService.getStaticConfig(EnumConfigType.SYSTEM_CONFIG,
                "emotion_icon." + emotion);
    }

    public static String packUrl(String content) {
        // Pattern ptnUrl =
        // Pattern.compile("http://www.heiyan.com/[^\\s\u4E00-\u9FA5]*");
        if (content == null)
            return null;
        Pattern ptnUrl = Pattern.compile("https?://[\\w/?+&%#=,:.-]+");
        Matcher matcher = ptnUrl.matcher(content);
        StringBuilder contentBuffer = new StringBuilder();
        int begin = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            contentBuffer.append(content.substring(begin, start));
            begin = end;

            String url = matcher.group();
            if (!url.toLowerCase().endsWith("gif")
                    && !url.toLowerCase().endsWith("jpg")
                    && !url.toLowerCase().endsWith("png"))
                url = "<a target=\"_blank\" class=\"blue\" href=\"" + url
                        + "\">" + url + "</a>";
            contentBuffer.append(url);
        }
        contentBuffer.append(content.substring(begin, content.length()));
        return contentBuffer.toString();
    }

    public static String filterResourceDefault(String content, int objectId,
                                               EnumObjectType objectType) {
        Pattern ptnArticle = Pattern
                .compile("(<\u56FE\u7247\\d+>)|(<\u97f3\u4e50\\d+>)|(<\u89C6\u9891\\d+>)|(<a[^>]*>[^<]*</a>)|(<[^<^>]*>)");
        Matcher matcher = ptnArticle.matcher(content);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                int icon = Integer.valueOf(matcher.group(1).substring(3,
                        matcher.group(0).length() - 1));
                ResourceService.service.getResource(objectId, objectType,
                        EnumResourceType.MUSIC, icon);
                Resouce image = ResourceService.service.getResource(objectId,
                        objectType, EnumResourceType.IMAGE, icon);
                if (image != null) {
                    String path = image.getImageUrlDefault();
                    if (path != null) {
                        String css = "";
                        switch (EnumPositionType.getEnum(image.getPosition())) {
                            case LEFT:
                                css = "al";
                                break;
                            case CENTER:
                                css = "ac";
                                break;
                            case RIGHT:
                                css = "ar";
                                break;
                            default:
                                break;
                        }
                        String html = new String();
                        html = "<div class='media-item media-picture " + css
                                + "'>" + "<img alt='" + image.getDescription()
                                + "' src='" + Constants.IMG_SERVER_DOMAIN
                                + path + "' />" + "<p class='desc'>"
                                + image.getDescription() + "</p>" + "</div>";

                        content = content.replace(matcher.group(1), html);
                    }
                } else
                    content = content.replace(matcher.group(1), "");
            } else if (matcher.group(2) != null) {
                String muscRxp = matcher.group(2);
                int icon = Integer.valueOf(muscRxp.substring(3, matcher
                        .group(0).length() - 1));
                Resouce music = ResourceService.service.getResource(objectId,
                        objectType, EnumResourceType.MUSIC, icon);
                if (music != null) {
                    String url = music.getObjectUrl();
                    if (url != null && !"".equals(url)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("<div class='media-item media-music'><table width=\"100%\"><tbody><tr><td align=\"left\">");

                        if (url.startsWith("http://www.xiami.com"))
                            sb.append("<embed height=\"33\" width=\"275\" wmode=\"transparent\" type=\"application/x-shockwave-flash\" src="
                                    + url + "></embed>");
                        else if (url.startsWith("http://box.baidu.com"))
                            sb.append("<embed height=\"95\" width=\"400\" wmode=\"transparent\" type=\"application/x-shockwave-flash\" src="
                                    + url + "></embed>");

                        sb.append("</td></tr>");

                        if (music.getDescription() != null
                                && !"".equals(music.getDescription())) {
                            sb.append("<tr><td><p class='desc'>");
                            sb.append(music.getDescription());
                            sb.append("</p></td></tr>");
                        }
                        sb.append("</tbody></table></div>");

                        content = content.replace(muscRxp, sb.toString());

                    } else
                        content = content.replace(muscRxp, "");
                } else
                    content = content.replace(muscRxp, "");
            } else if (matcher.group(3) != null) {
                String videoRxp = matcher.group(3);
                int icon = Integer.valueOf(videoRxp.substring(3,
                        matcher.group(0).length() - 1));
                Resouce video = ResourceService.service.getResource(objectId,
                        objectType, EnumResourceType.VIDEO, icon);
                if (video != null) {
                    String url = video.getObjectUrl();
                    if (url != null && !"".equals(url)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("<div class='media-item media-music'><table width=\"100%\"><tbody><tr><td align=\"left\">");

                        sb.append("<embed height=\"300\" width=\"400\" wmode=\"transparent\" type=\"application/x-shockwave-flash\"  src="
                                + url + ">");

                        sb.append("</td></tr>");

                        if (video.getDescription() != null
                                && !"".equals(video.getDescription())) {
                            sb.append("<tr><td><p class='desc'>");
                            sb.append(video.getDescription());
                            sb.append("</p></td></tr>");
                        }
                        sb.append("</tbody></table></div>");

                        content = content.replace(videoRxp, sb.toString());

                    } else
                        content = content.replace(videoRxp, "");
                } else
                    content = content.replace(videoRxp, "");
            } else if (matcher.group(4) != null) {

                continue;
            } else if (matcher.group(5) != null) {
            }

        }
        return content;
    }

    public static String filterResource4android(String content, int objectId,
                                                EnumObjectType objectType) {
        Pattern ptnArticle = Pattern.compile("(<\u56FE\u7247\\d+>)");
        Matcher matcher = ptnArticle.matcher(content);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                int icon = Integer.valueOf(matcher.group(1).substring(3,
                        matcher.group(0).length() - 1));
                Resouce image = ResourceService.service.getResource(objectId,
                        objectType, EnumResourceType.IMAGE, icon);
                if (image != null) {
                    String path = image.getImageUrlLarge();
                    if (path != null) {
                        content = content.replace(matcher.group(1),
                                "<img src=\"" + path + "\"/>");
                    }
                } else
                    content = content.replace(matcher.group(1), "");
            }

        }
        return content;
    }

    public static String parseResource4android(String content, int objectId,
                                               EnumObjectType objectType) {
        String result = "";

        Pattern ptnArticle = Pattern.compile("(<\u56FE\u7247\\d+>)");
        Matcher matcher = ptnArticle.matcher(content);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                int icon = Integer.valueOf(matcher.group(1).substring(3,
                        matcher.group(0).length() - 1));
                Resouce image = ResourceService.service.getResource(objectId,
                        objectType, EnumResourceType.IMAGE, icon);
                if (image != null) {
                    String path = image.getImageUrlLarge();
                    if (path != null) {
                        String[] split = content.split(matcher.group(1), 2);
                        result += (split[0] + "<img>");
                        result += (Constants.IMG_SERVER_DOMAIN + path + "<img>");
                        content = split[1];
                    }
                } else
                    content = content.replace(matcher.group(1), "");
            }

        }
        result += content;
        return result;
    }

    public static String filterResource(String content, int objectId,
                                        EnumObjectType objectType) {
        return filterResource(content, objectId, objectType, true);
    }

    public static String filterResource(String content, int objectId,
                                        EnumObjectType objectType, boolean escapeHtml) {
        Pattern ptnArticle = escapeHtml ?
                Pattern.compile("(<\u56FE\u7247\\d+>)|(<\u97f3\u4e50\\d+>)|(<\u89C6\u9891\\d+>)|(<a[^>]*>[^<]*</a>)|(<[^<^>]*>)")
                :
                Pattern.compile("(<\u56FE\u7247\\d+>)|(<\u97f3\u4e50\\d+>)|(<\u89C6\u9891\\d+>)");
        Matcher matcher = ptnArticle.matcher(content);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                int icon = Integer.valueOf(matcher.group(1).substring(3,
                        matcher.group(0).length() - 1));
                ResourceService.service.getResource(objectId, objectType,
                        EnumResourceType.MUSIC, icon);
                Resouce image = ResourceService.service.getResource(objectId,
                        objectType, EnumResourceType.IMAGE, icon);
                if (image != null) {
                    String path = image.getImageUrlLarge();
                    if (path != null) {
                        String css = "";
                        switch (EnumPositionType.getEnum(image.getPosition())) {
                            case LEFT:
                                css = "al";
                                break;
                            case CENTER:
                                css = "ac";
                                break;
                            case RIGHT:
                                css = "ar";
                                break;
                            default:
                                break;
                        }
                        String html = new String();
                        html = "<div class='media-item media-picture " + css
                                + "'>" + "<img alt='" + image.getDescription()
                                + "' src='" + (ImageService.isImageOss() ? Constants.OSS_HEIYAN_DOMAIN : Constants.IMG_SERVER_DOMAIN)
                                + path + "' />" + "<p class='desc'>"
                                + image.getDescription() + "</p>" + "</div>";

                        content = content.replace(matcher.group(1), html);
                    }
                } else
                    content = content.replace(matcher.group(1), "");
            } else if (matcher.group(2) != null) {
                String muscRxp = matcher.group(2);
                int icon = Integer.valueOf(muscRxp.substring(3, matcher
                        .group(0).length() - 1));
                Resouce music = ResourceService.service.getResource(objectId,
                        objectType, EnumResourceType.MUSIC, icon);
                if (music != null) {
                    String url = music.getObjectUrl();
                    if (url != null && !"".equals(url)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("<div class='media-item media-music'><table width=\"100%\"><tbody><tr><td align=\"left\">");

                        if (url.startsWith("http://www.xiami.com"))
                            sb.append("<embed height=\"33\" width=\"275\" wmode=\"transparent\" type=\"application/x-shockwave-flash\" src="
                                    + url + "></embed>");
                        else if (url.startsWith("http://box.baidu.com"))
                            sb.append("<embed height=\"95\" width=\"400\" wmode=\"transparent\" type=\"application/x-shockwave-flash\" src="
                                    + url + "></embed>");

                        sb.append("</td></tr>");

                        if (music.getDescription() != null
                                && !"".equals(music.getDescription())) {
                            sb.append("<tr><td><p class='desc'>");
                            sb.append(music.getDescription());
                            sb.append("</p></td></tr>");
                        }
                        sb.append("</tbody></table></div>");

                        content = content.replace(muscRxp, sb.toString());

                    } else
                        content = content.replace(muscRxp, "");
                } else
                    content = content.replace(muscRxp, "");
            } else if (matcher.group(3) != null) {
                String videoRxp = matcher.group(3);
                int icon = Integer.valueOf(videoRxp.substring(3,
                        matcher.group(0).length() - 1));
                Resouce video = ResourceService.service.getResource(objectId,
                        objectType, EnumResourceType.VIDEO, icon);
                if (video != null) {
                    String url = video.getObjectUrl();
                    if (url != null && !"".equals(url)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("<div class='media-item media-music'><table width=\"100%\"><tbody><tr><td align=\"left\">");

                        sb.append("<embed height=\"300\" width=\"400\" wmode=\"transparent\" type=\"application/x-shockwave-flash\"  src="
                                + url + ">");

                        sb.append("</td></tr>");

                        if (video.getDescription() != null
                                && !"".equals(video.getDescription())) {
                            sb.append("<tr><td><p class='desc'>");
                            sb.append(video.getDescription());
                            sb.append("</p></td></tr>");
                        }
                        sb.append("</tbody></table></div>");

                        content = content.replace(videoRxp, sb.toString());

                    } else
                        content = content.replace(videoRxp, "");
                } else
                    content = content.replace(videoRxp, "");
            } else if (matcher.group(4) != null) {

                continue;
            } else if (matcher.group(5) != null) {
                if(escapeHtml){
                    String str = matcher.group(5);
                    content = content.replace(str, HtmlUtils.htmlEscape(str));
                }
            }

        }
        return content;
    }

    /**
     * 得到内容里面的第一个资源
     *
     * @author chenck
     */
    public static Resouce getFirstResource(String content, int objectId,
                                           EnumObjectType objectType) {
        if (content == null || content.equals(""))
            return null;
        Pattern ptnArticle = Pattern
                .compile("(<\u56FE\u7247\\d+>)|(<\u97f3\u4e50\\d+>)|(<\u89C6\u9891\\d+>)|(<a href=[^>]*>[^<]+</a>)|(<[^<^>]*>)");
        Matcher matcher = ptnArticle.matcher(content);
        Resouce res = null;
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                int icon = Integer.valueOf(matcher.group(1).substring(3,
                        matcher.group(0).length() - 1));
                ResourceService.service.getResource(objectId, objectType,
                        EnumResourceType.MUSIC, icon);
                res = ResourceService.service.getResource(objectId, objectType,
                        EnumResourceType.IMAGE, icon);
                if (res != null) {
                    String path = res.getImageUrlSmall();
                    if (path != null) {
                        break;
                    }
                }
            } else if (matcher.group(2) != null) {
                String muscRxp = matcher.group(2);
                int icon = Integer.valueOf(muscRxp.substring(3, matcher
                        .group(0).length() - 1));
                res = ResourceService.service.getResource(objectId, objectType,
                        EnumResourceType.MUSIC, icon);
                if (res != null) {
                    String url = res.getObjectUrl();
                    if (url != null && !"".equals(url)) {
                        break;
                    }
                }

            } else if (matcher.group(3) != null) {
                String videoRxp = matcher.group(3);
                int icon = Integer.valueOf(videoRxp.substring(3,
                        matcher.group(0).length() - 1));
                res = ResourceService.service.getResource(objectId, objectType,
                        EnumResourceType.VIDEO, icon);
                if (res != null) {
                    String url = res.getObjectUrl();
                    if (url != null && !"".equals(url)) {
                        break;
                    }
                }
            }

        }
        return res;
    }

    /**
     * 通过音乐的Url得到对应的flash html
     *
     * @param url
     * @return
     */
    public static String getMusicResouceUrl(String url) {
        if (url == null)
            return url;
        if (url.startsWith("http://www.xiami.com"))
            url = "<embed height=\"33\" width=\"275\" wmode=\"transparent\" type=\"application/x-shockwave-flash\" src=\""
                    + url + "\"></embed>";
        else if (url.startsWith("http://box.baidu.com"))
            url = "<embed height=\"95\" width=\"400\" wmode=\"transparent\" type=\"application/x-shockwave-flash\" src=\""
                    + url + "\"></embed>";
        return url;
    }

    public static String addReplyName(String summary) {
        if (summary == null)
            return null;
        Matcher m = ptnAt.matcher(summary);
        while (m.find()) {
            String name = m.group(0).substring(1);
            String url = "<a class=\"reply-name\" href=\"/name/" + name + "\">"
                    + m.group(0) + "</a>";
            summary = summary.replaceFirst(m.group(0), url);
        }
        return summary;
    }

    public static String addEmotion(String summary) {
        if (summary == null)
            return null;
        Matcher m = ptnEmotion.matcher(summary);
        while (m.find()) {
            String emotion = m.group(0).substring(1, m.group(0).length() - 1);
            if (getEmotionUrl(emotion) != null) {
                String url = "<img src=\"" + getEmotionUrl(emotion)
                        + "\" alt=\"" + emotion + "\">";
                summary = summary.replace(m.group(0), url);
            } else
                continue;
        }
        return summary;
    }

    public static String addEmotionWithJs(String summary) {
        if (summary == null)
            return null;
        Matcher m = ptnEmotion.matcher(summary);
        while (m.find()) {
            String emotion = m.group(0).substring(1, m.group(0).length() - 1);
            if (getEmotionUrl(emotion) != null) {
                String url = "<img src=\\\"" + getEmotionUrl(emotion)
                        + "\\\" alt=\\\"" + emotion + "\\\">";
                summary = summary.replace(m.group(0), url);
            } else
                continue;
        }
        return summary;
    }

    public static String addPContent(String str) {
        if (str == null || "".equals(str))
            return str;
        return "<p>"
                + str.trim().replaceAll("\\r", "").replaceFirst("[　\\s]*", "")
                .replaceAll("\\n[　\\s]*", "</p><p>") + "</p>";
    }

    private static Pattern p = Pattern.compile("[　]+");

    public static String trimContent(String str) {
        if (str == null)
            return str;

        String content = "";
        String[] split = str.split("\n");
        for (String tem : split) {
            if ("".equals(tem))
                continue;
            Matcher m = p.matcher(tem);
            if (m.matches())
                continue;
            content = content + tem.replaceFirst("[　]*", "　　") + "\n";
        }
        if (content.length() > 0)
            return content.substring(0, content.length() - 1);
        return content;
    }

    // 正在使用
    public static List<Volume> getVolumeListR(BufferedReader reader)
            throws IOException {
        List<Chapter> chapterList = new ArrayList<Chapter>();
        List<Volume> volumeList = new ArrayList<Volume>();
        String temp = "";
        String content = "";
        int i = 0;
        while ((temp = reader.readLine()) != null) {
            temp = temp.trim();
            // System.out.println("instart ["+temp + "]"
            // +temp.lastIndexOf("#")+temp.indexOf("#"));
            if (temp.startsWith("##") && !temp.startsWith("###")) {
                Volume v = new Volume();
                v.setName(temp.replace("##", ""));
                v.setList(new ArrayList<Chapter>());
                // System.out.println("name========== "+v.getName());
                volumeList.add(v);
            } else if (temp.startsWith("###")) {
                Chapter c = new Chapter();
                c.setName(temp.replaceAll("###", ""));
                chapterList.add(c);

                if (i > 0)
                    chapterList.get(chapterList.size() - 1).setContent(content);
                content = "";

                if (volumeList.size() == 0) {
                    Volume v = new Volume();
                    v.setList(new ArrayList<Chapter>());
                    volumeList.add(v);
                    v.getList().add(c);
                } else {
                    volumeList.get(volumeList.size() - 1).getList().add(c);
                }

                i++;

            } else {
                Matcher m = p.matcher(temp);
                if (m.matches())
                    continue;
                // 替换二个以上的空格
                String t = "　　"
                        + temp.replaceAll("[　]+", "").replaceAll("!#", "");
                content = content + "\n" + t;
            }
            // 结束
            if (chapterList.size() > 0) {
                chapterList.get(chapterList.size() - 1).setContent(content);
            }
            if (volumeList.size() > 1 && volumeList.get(0).getName() == null)
                volumeList.get(0).setName("卷");

        }

		/*
		 * for (Volume c : volumeList) { for (Chapter ch : c.getList()) {
		 * System.out.println(ch.getName()); if (ch.getContent() != null)
		 * System.out.println(ch.getContent().length()); } }
		 */

        return volumeList;
    }

    public static String[] splitTags(String tagStr) {
        if (tagStr == null)
            tagStr = "";
        String[] split = tagStr.trim().split("[,\uFF0C\\s\\u3000]");
        Set<String> wordSet = new HashSet<String>();
        for (String word : split) {
            if (!"".equals(word))
                wordSet.add(word);
        }
        String[] tags = new String[wordSet.size()];
        wordSet.toArray(tags);
        return tags;
    }

    public static String getColor(String str) {
        if (str == null)
            return "#000000";
        else {
            String red = str.substring(0, 2);
            String green = str.substring(2, 4);
            String blue = str.substring(4, 6);

            int r = Integer.parseInt(red, 16);
            int g = Integer.parseInt(green, 16);
            int b = Integer.parseInt(blue, 16);

            if (r < 13 || g < 9 || b < 13) {
                r = r + 26;
                g = g + 18;
                b = b + 26;
            } else {
                r = r - 13;
                g = g - 9;
                b = b - 13;
            }
            return "";
        }
    }

    public static void main(String args[]) throws IOException {
        // String cn_words = "^[\\w]+";//^(?![0-9]+$)表示不能全部是数字
        // Pattern p = Pattern.compile(cn_words);
        // String email = "";
        // String userName = "test123";
        // for(int i = 0; i<userName.length(); i++) {
        // if(p.matcher(userName.charAt(i) + "").matches()) {
        // email = email + userName.charAt(i);
        // }
        // }
        // String content =
        // "<a href=\"http://select.yeeyan.org/view/41021/395843\">希望自己23个粉丝中有朋友能为我呼救</a>";
        // String htmlContent = WingsStrUtil.filterResource(content, 0,
        // EnumObjectType.TOPIC);
        // System.out.println(new Date().getTime()/1000);
        // System.out.println(new Date(1392190800000l));

        // String verify = "123456789";
        // String key = "ik#F&M4w&?W#9AZ";
        // int pid = 10;
        // int chapterId = 11639;
        // String str = MD5.MD5(chapterId + "#" + pid + "#" + key);
        // String mySign = MD5.MD5(verify + "#" + str);
        // System.out.println("/data/api/chapter?pid=10&book_id=4912&chapter_id=95675&limit=10&verify="
        // + verify + "&sign=" + mySign);

        // String url = "/book/5503l2.jpg";
        // File file = new File("temp.jpg");
        // BufferedImage bufferImg = ImageIO.read(new URL("http",
        // Constants.IMAGE_SERVER_HOST, 80, url.substring(0,
        // url.lastIndexOf('l')) + "p" + url.substring(url.lastIndexOf('l'))));
        // BufferedImage newImage = new BufferedImage(500, 720,
        // bufferImg.getType());
        // Graphics g = newImage.getGraphics();
        // g.drawImage(bufferImg, 0, 0, 500, 720, null);
        // g.dispose();
        // ImageIO.write(newImage, "jpg", file);
        // WingsStrUtil.getToken(1001+""+123);
        // System.out.println(1001+""+123);
        // System.out.println(WingsStrUtil.getTokenIds("广 东匠圾"));
        // System.out.println("***********************************");
        // WingsStrUtil.getToken(204+""+123);
        // System.out.println(204+""+123);
        // System.out.println(WingsStrUtil.getTokenIds("华估场"));
        // System.out.println("***********************************");
        // WingsStrUtil.getToken(204+""+12300);
        // System.out.println(WingsStrUtil.getTokenIds("华估场圣"));
        // System.out.println(WingsStrUtil.getToken("2960781568813"));
        // System.out.println(WingsStrUtil.getTokenIds("女在系叨吗沟才"));
    }

    public static String addContentWaterMark(String content, final int userid, final int chapterId, boolean isFree, boolean isChapter){

        if( StringUtil.strIsNull(content) ){
            return content;
        }

        content = HtmlUtils.htmlEscape(content);

        final StringBuffer hexUid = new StringBuffer(Integer.toHexString(userid));
        final int hexUidLength = hexUid.length();

        content = content.replaceAll("&lt;br/&gt;", "<br/>").replaceAll("\\r", "").replaceFirst("[\\u3000\\s]*", "").replaceAll("\\n[\\u3000\\s]*", "###");

        boolean useMark = userid != 0;

        StringBuffer sb = new StringBuffer(content);
        if( useMark && isChapter ){

            //统一转成简体
            StringUtil.tranChs(sb, false);

            StringUtil.tranChs(sb, true, new StringUtil.TransConditions() {
                /*
                 * 简体转繁体水印与
                 */
                int textTimes = 0;
                boolean start = false;
                int[] tArray = new int[hexUidLength];
                char[] tTextArray = new char[hexUidLength];
                @Override
                public boolean whether(StringBuffer sb, int index, int count, char dst, char src) {
                    int result = (count-1) % 16;
                    if( result == 0 ){
                        start = true;
                    }
                    if( start ){
                        if( textTimes < hexUidLength ){
                            if( Integer.toHexString(result).equals( String.valueOf(hexUid.charAt(textTimes)) ) ){
                                start = false;
                                tTextArray[textTimes] = dst;
                                tArray[textTimes] = index;
                                textTimes++;
                            }
                        }
                    }
                    return false;
                }

                /*
                 * 逗号转句号水印
                 */
                int mark = 0; //段数
                ArrayList<Integer> linePosition = new ArrayList<Integer>();
                int pointTimes = 0;
                int pointCount = 0;
                int pointResult = 0;
                int[] pArray = new int[hexUidLength];
                @Override
                public void each(StringBuffer sb, int index, char text, boolean isChinese) {
                    if( text == '#'){
                        mark++;
                        if( mark == 3){
                            mark = 0;
                            linePosition.add(index);
                        }
                    }else if( text == '，' ) {
                        pointCount++;
                        pointResult = (pointCount-1) % 16;
                        if( pointTimes < hexUidLength ){
                            if( Integer.toHexString(pointResult).equals( String.valueOf(hexUid.charAt(pointTimes)) ) ){
                                pArray[pointTimes] = index;
                                pointTimes++;
                            }
                        }
                    }
                }

                @Override
                public void end(StringBuffer sb){

                    //如果可繁体水印长度符合16进制ID长度，转换
                    if( textTimes == hexUidLength ){
                        for(int i = 0 ; i < hexUidLength; i++){
                            sb.setCharAt(tArray[i], tTextArray[i]);
                        }
                    }

                    //如果逗号长度符合16进制ID长度，转换
                    if( pointTimes == hexUidLength ){
                        for(int i = 0 ; i < hexUidLength; i++){
                            sb.setCharAt(pArray[i], '。');
                        }
                    }

                    //直接文本插入水印
                    if( linePosition.size() > 0 ){
                        int insertIndex = chapterId % linePosition.size();
                        if( insertIndex < 2 ){
                            insertIndex = 3;
                        }
                        if( linePosition.size() >2 ){
                            String tokens = WingsStrUtil.getToken( chapterId + userid + "" );
                            sb.insert(linePosition.get(insertIndex) - 2, tokens + "。");
                        }
                    }
                }
            });
        }

        if( userid != 0 ){
			/*
			 * 增加解码难度
			 */
            //异或码
            int x = userid;
            //如果用户16进制ID长度大于2，取两头16进制合并字串的10进制作为异或码
            if( hexUidLength > 2 ){
                x = Integer.parseInt(hexUid.charAt(0) + "" + hexUid.charAt(hexUidLength-1), 16);
            }
            //x = x < 10 ? x << 3 : x;

            for( int i = 0, len = sb.length(); i < len ; i++ ){
                char c = sb.charAt(i);
                if( !StringUtil.isChinese(c) ) continue;
                sb.setCharAt(i, Character.toChars( c^x )[0]);
            }
        }

        return sb.toString();
    }

    public static String getToken(String tokenIds) {
        if (StringUtil.strIsNull(tokenIds))
            return "";
        // '0-9'标示的字符
        String token09 = "扛血巴才圾亡技弟划号";
        // '00-99'标示的字符
        String token099 = "圣向反找农何页名吐长私广杂东协叨大助双肝华每刚场有住介尤引匠乒坑阵岛呆贞纵团女巨共估台尽布叉休冬夹状木斤他扔讨系池庄来豆叼低宏上亚以土役吗记围扑丽余鸟帅节亩边妖见沟肠狂吉医爪欢厅在史投讽岁序乐央丰丸司";
        StringBuffer tokenbuf = new StringBuffer();

        Double times = new Double(tokenIds.length());
        times = Math.ceil(times / 2);
        for (int i = 0; i < times; i++) {
            int start = i * 2;
            int end = ((i + 1) * 2) > tokenIds.length() ? i * 2 + 1 : i * 2 + 2;
            Integer index = new Integer(tokenIds.substring(start, end));
            if ((i + 1) * 2 > tokenIds.length()) {// 如果解析出的ids是0-9
                tokenbuf.append(token09.substring(index, index + 1));
            } else {
                tokenbuf.append(token099.substring(index, index + 1));
            }

        }
        return tokenbuf.toString();
    }

    public static String getTokenIds(String tokens) {
        if (StringUtil.strIsNull(tokens))
            return "";
        // '0-9'标示的字符
        String token09 = "扛血巴才圾亡技弟划号";
        // '00-99'标示的字符
        String token099 = "圣向反找农何页名吐长私广杂东协叨大助双肝华每刚场有住介尤引匠乒坑阵岛呆贞纵团女巨共估台尽布叉休冬夹状木斤他扔讨系池庄来豆叼低宏上亚以土役吗记围扑丽余鸟帅节亩边妖见沟肠狂吉医爪欢厅在史投讽岁序乐央丰丸司";
        StringBuffer tokenIdsbuf = new StringBuffer();
        for (int i = 0; i < tokens.length(); i++) {
            String token = tokens.substring(i, i + 1);
            if (token09.contains(token)) {
                tokenIdsbuf.append(token09.indexOf(token));
            } else {
                String index = token099.indexOf(token) < 10 ? 0 + ""
                        + token099.indexOf(token) : token099.indexOf(token)
                        + "";
                tokenIdsbuf.append(index);
            }
        }
        return tokenIdsbuf.toString();
    }

    public static String getUnityChapterName(int index, String name) {
        String config = ConfigService.getStaticConfig(
                EnumConfigType.SYSTEM_CONFIG, "chapter_name_regex");
        // String config =
        // "^(\u7b2c*【*[\\d\u4e00-\u96f6]*\u7ae0*[：、，。 ；…—\\p{Punct}\\s】]+).+$,^(chapter[\\d\u4e00-\u96f6]*[：、，。 ；…—\\p{Punct}\\s]+).+$";
        // String config = "^(第*【*[\d一-零]*章*[：、，。
        // ；…—\p{Punct}\s】]+).+$,^(chapter[\s\d一-零]*[：、，。 ；…—\p{Punct}\s]+).+$";

        String[] split = config.split(",");
        if (split == null)
            return name;
        for (String prefix : split) {
            Pattern p = Pattern.compile(prefix);
            Matcher m = p.matcher(name);
            if (m.matches()) {
                return name.replace(m.group(1), "第" + index + "章 ");
            }
        }
        return "第" + index + "章 " + name;
    }

    public static String getLocalIP() {
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if(byName != null){
                Enumeration<InetAddress> es = NetworkInterface.getByName("eth1")
                        .getInetAddresses();
                while (es.hasMoreElements()) {
                    InetAddress ip = es.nextElement();
                    if (ip != null && ip instanceof Inet4Address)
                        return ip.getHostAddress();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void test() {
        String str = "柏林的天气一如既往的温和，苏程言在茶餐厅外面闲闲地踱步，如果不是地点问题，旁人丝毫不会看出来这中国[[b]b]女孩子在等[[/b]/b]人[/b]。";
        Pattern ptn = Pattern.compile("([b\\[\\]]+)");
        Matcher m = ptn.matcher(str);
        if (m.matches()) {
            // for(int i=1; i<=m.groupCount(); i++)
            System.out.println(m.group(0));
        }
    }

    public static String getOneRandom(String oldOneRandom) {
        String ram = Math.random() + "0000000000000000";
        while (ram.equals(oldOneRandom)) {
            ram = Math.random() + "0000000000000000";
        }
        return ram.substring(0, Constants.RANDOM_ONE_LENGTH);
    }

    public static boolean isCrabed(String word) {
        List<String> crabList = CrabTreeHandler.getCrabList(word);
        for (String crab : crabList) {
            if (word.indexOf(crab) != -1) {
                return true;
            }
        }
        return false;
    }

    public static long getObjectIt(long objectId, EnumObjectType objectType) {
        return Long.parseLong(objectId + get4ObjType(objectType == null ? 0 : objectType.getValue()));
    }

    public static String get4ObjType(int value) {
        if (value < 10)
            return "000" + value;
        else if (value < 100)
            return "00" + value;
        else if (value < 1000)
            return "0" + value;
        else
            return "" + value;
    }

    public static String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            return request.getRemoteAddr();
        }
        if(ip != null && ip.contains(",")){
            ip = ip.split(",")[0];
        }
        return ip;
    }

    public static byte getByteObjectType(long objectIt) {
        objectIt = objectIt < 0 ? -objectIt : objectIt;
        return (byte) (objectIt % 10000);
    }

    public static EnumObjectType getObjectType(long objectIt) {
        return EnumObjectType.getEnum(getByteObjectType(objectIt));
    }

    public static int getObjectId(long objectIt) {
        objectIt = objectIt < 0 ? -objectIt : objectIt;
        return (int) (objectIt / 10000);
    }

    public static double get3PointRealMoney(double money) {
        return Math.round(money * 1000) / 1000.0;
    }

    public static double get2PointRealMoney(double money) {
        return Math.round(money * 100) / 100.0;
    }

    public static double getRealMoney(int realBi) {
        return Math.round(realBi) / 100.0;
    }

    public static int getRealBi(double money) {
        return (int) Math.round(money * 100);
    }

    public static long getRefundLongObjectIt(int bookId, int day) {
        return Long.parseLong(bookId + "" + day);
    }

    public static int getBookIdFromRefundLongObjectIt(long objectIt) {
        return (int) (objectIt / 100000000); // 因为天为8位
    }

    public static int getDayFromRefundLongObjectIt(long objectIt) {
        return (int) (objectIt % 100000000); // 因为天为8位
    }

    public static int getRefundDay(Date date) {
        return Integer.valueOf(DateUtil.getDateyyyyMMdd(date));
    }

    public static double getCashMoney(double money) {
        money = get3PointRealMoney(money);
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.##");
        df.setRoundingMode(RoundingMode.FLOOR);
        return Double.valueOf(df.format(money));
    }

    public static List<Money> listMoneyDetailFromDescption(String desc) {
        List<Money> list = new ArrayList<Money>();
        JSONObject json;
        try {
            json = new JSONObject(desc);
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String[] split = key.split("#");

                MoneyID id = new MoneyID();
                id.setUserId(Integer.valueOf(split[0]));
                id.setObjectIt(Long.valueOf(split[1]));
                id.setType(Integer.valueOf(split[2]));
                list.add(new Money(id, json.getDouble(key)));
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        return list;
    }

    public static int getSystemTimeSec() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static long getAttributeId(long objectId, EnumObjectType objectType,
                                      EnumAttributeType type) {
        // System.out.println((long)objectId * 100000000 + (objectType == null ?
        // 0 :objectType.getValue()) * 10000 + (type == null ? 0
        // :type.getValue()));
        return (long) objectId * 100000000
                + (objectType == null ? 0 : objectType.getValue()) * 10000
                + (type == null ? 0 : type.getValue());
    }

    public static int[] removeRepeat(int[] id) {
        Map map = new HashMap();
        List list = new ArrayList();

        for (int i = 0; i < id.length; i++) {
            if (map.get("" + id[i]) == null) {
                list.add(id[i]);
                map.put("" + id[i], "");
            }
        }
        int[] temp = new int[list.size()];
        return temp;
    }

    public static List removeRepeat(List list) {
        Map map = new HashMap();
        List tempList = new ArrayList();

        for (int i = 0; i < list.size(); i++) {
            if (map.get("" + list.get(i)) == null) {
                tempList.add(list.get(i));
                map.put("" + list.get(i), "");
            }
        }
        return tempList;
    }

    public static String getAllPageTag(String input) {
        Pattern ptn = Pattern
                .compile("[^\\u4e00-\\u9fa5]*([\\u4e00-\\u9fa5]+).*");
        Matcher m = ptn.matcher(input);
        if (m.matches()) {
            return m.group(1);
        }
        return "";
    }

    private static String ptnQuery = "[\\*\\$\\+\\.\\?\\[\\]\\\\\\/\\^\\{\\}\\|\\(\\)]";

    public static String filterQueryString(String str) {
        if (str == null || "".equals(str))
            return str;
        return str.replaceAll(ptnQuery, "");
    }

    public static String getObjectFieldValue(Object obj, String field) {
        try {
            return BeanUtil.getProperty(obj, field);
        } catch (Exception e) {
            return null;
        }
    }

    public static void setObjectFieldValue(Object obj, String field,
                                           Object value) {
        try {
            BeanUtil.setProperty(obj, field, value);
        } catch (Exception e) {
        }
    }

    public static String getPaypalStr(String str, String charset)
            throws UnsupportedEncodingException {
        if (str == null)
            return null;
        return URLDecoder.decode(str, charset);
    }

    public static String showTime(Date date) {
        if (date == null)
            return null;

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        DateFormat df;
        // 没有隔天
        if (date.getTime() > c.getTimeInMillis()) {
            // 只能定义为float或者double
            float diffSecond = (System.currentTimeMillis() - date.getTime()) / 1000;
            if (diffSecond <= 10) {
                return 10 + "秒前";
            } else if (diffSecond <= 50) {
                return (int) Math.ceil(diffSecond / 10) * 10 + "秒前";
            } else if (diffSecond < 3600) {// 一小时内
                return (int) Math.ceil(diffSecond / 60) + "分钟前";
            } else if (diffSecond < 60 * 60 * 24) {// 10小
                df = new SimpleDateFormat("HH:mm");
                return "今天" + df.format(date);
            }
        }
        // 昨天
        c.add(Calendar.DAY_OF_MONTH, -1);
        if (date.getTime() > c.getTimeInMillis()) {
            df = new SimpleDateFormat("HH:mm");
            return "昨天" + df.format(date);
        }

        if (c.get(Calendar.YEAR) == 1900 + date.getYear())
            df = new SimpleDateFormat("MM-dd HH:mm");
        else
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return df.format(date);
    }

    public static String showChapterListDate(Date date) {
        if (date == null)
            return null;

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        DateFormat df;
        if (c.get(Calendar.YEAR) == 1900 + date.getYear())
            df = new SimpleDateFormat("MM-dd");
        else
            df = new SimpleDateFormat("yy-MM-dd");
        return df.format(date);
    }

    public static double getTaxMoney(double totalMoney) {
        if (totalMoney <= 800)
            return 0;
        else if (totalMoney <= 4000)
            return WingsStrUtil.get2PointRealMoney((totalMoney - 800) * 0.14);
        else
            return WingsStrUtil.get2PointRealMoney(totalMoney * 0.8 * 0.14);
    }

    public static Map<Integer, Double> getMapFromJson(String remark) {
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        if (remark == null || "".equals(remark))
            return map;
        JSONObject json;
        try {
            json = new JSONObject(remark);
            Iterator keys = json.keys();
            while (keys.hasNext()) {
                String key = "" + keys.next();
                map.put(StringUtil.str2Int(key), json.getDouble(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getRequestHost(HttpServletRequest request) {
        String serverName = CookieUtil.getServerName(request);
        return "http://" + serverName + ":"
                + request.getServerPort();
    }

    /**
     * 获取作品标签
     * <p>Description:</p>
     * @param tags
     * @param junction 多个标签见链接发符号
     * @return
     */
    public static String getBookTags(String tags , String junction){
        String tag = "";
        if(StringUtil.strNotNull(tags)){
            String tagss[] = tags.split(" ");
            for(String str : tagss){
                if(StringUtil.strNotNull(str)){
                    if(StringUtil.strNotNull(tag))
                        tag += junction+str;
                    else
                        tag += str;
                }
            }
        }
        return tag;
    }

    public static String getTopUpExtra(int userId, int spreadId) {
        return userId + "_" + spreadId;
    }

    public static int getTopUpUserId(String extra) {
        if(StringUtil.strIsNull(extra))
            return 0;
        String[] split = extra.split("_");
        return StringUtil.str2Int(split[0]);
    }

    public static int getTopUpSpreadId(String extra) {
        if(StringUtil.strIsNull(extra))
            return 0;
        String[] split = extra.split("_");
        if(split.length < 2)
            return 0;
        return StringUtil.str2Int(split[1]);
    }

    /**
     * 生成随机密码
     * @param pwd_len 生成的密码的总长度
     * @return  密码的字符串
     */
    public static String getRandomPwd(int pwd_len) {

        final int  maxNum = 71;
        int i;  //生成的随机数
        int count = 0; //生成的密码的长度
        char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-',
                '=', '+', '*', ',', '.', '$', '#', '@'};

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while(count < pwd_len){

            //生成随机数，取绝对值，防止 生成负数，
            i = Math.abs(r.nextInt(maxNum));

            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count ++;
            }
        }

        return pwd.toString();
    }
}
