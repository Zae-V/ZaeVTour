package com.example.zaevtour;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

public class PlaceActivity extends AppCompatActivity {
    EditText edit;
    TextView text;
    XmlPullParser xpp;

    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_list);

        edit= (EditText)findViewById(R.id.searchEdit1);
        text= (TextView)findViewById(R.id.result);

    }
    public void mOnClick(View v){
        switch (v.getId()){
            case R.id.searchBtn1:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        data=getXmlData();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                text.setText(data);
                            }
                        });
                    }
                }).start();
                break;
        }
    }
    String getXmlData(){
        StringBuffer buffer=new StringBuffer();
        String str= edit.getText().toString();//EditText에 작성된 Text얻어오기
        String location = URLEncoder.encode(str);
        String query="%EC%A0%84%EB%A0%A5%EB%A1%9C";
        String key="lWATJCuA8E%2FENcTPyhkd%2Fb0rQWfZOWRcB58jRjd6FphwtghA1QsUobr3WoYi%2FJgLFcyplwdzKbKrv7Jo4G9aMw%3D%3D";
        String address = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/";
        String listType = "areaBasedList";
        String pageNo = "1";
        String numOfRows = "100";
        String mobileApp = "ZaeVTour";
        String mobileOS = "AND";
        String arrange = "A"; // (A=제목순, B=조회순, C=수정일순, D=생성일순) , 대표이미지가 반드시 있는 정렬 (O=제목순, P=조회순, Q=수정일순, R=생성일순)
        String contentTypeID = "39";
        String areaCode = "1"; // 서울시 = 1
        String listYN = "Y"; // (Y=목록, N=개수)
        String sigunguCode = ""; //시군구 코드

        /**
         * contentTypeID 종류
         *
         * 관광지 = 12
         * 문화시설 = 14
         * 행사/공연/축제 = 15
         * 여행코스 = 25
         * 레포츠 = 28
         * 숙박 = 32
         */

        String queryUrl = address + listType + "?"
                + "serviceKey=" + key
                + "&pageNo=" + pageNo
                + "&numOfRows=" + numOfRows
                + "&MobileApp=" + mobileApp
                + "&MobileOS=" + mobileOS
                + "&arrange=" + arrange
                + "&contentTypeId=" + contentTypeID
                + "&areaCode=" + areaCode
                + "&listYN=" + listYN;
//        String queryUrl="http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=lWATJCuA8E%2FENcTPyhkd%2Fb0rQWfZOWRcB58jRjd6FphwtghA1QsUobr3WoYi%2FJgLFcyplwdzKbKrv7Jo4G9aMw%3D%3D&pageNo=1&numOfRows=10&MobileApp=AppTest&MobileOS=AND&arrange=A&contentTypeId=39&areaCode=1&listYN=Y";
        try{
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("title")){
                            buffer.append("이름 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("tel")){
                            buffer.append("전화번호 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//category 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("addr1")){
                            buffer.append("주소 :");
                            xpp.next();
                            buffer.append(xpp.getText());//description 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("mapx")){
                            buffer.append("mapx :");
                            xpp.next();
                            buffer.append(xpp.getText());//telephone 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("mapy")){
                            buffer.append("mapy :");
                            xpp.next();
                            buffer.append(xpp.getText());//address 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n");//줄바꿈 문자 추가
                        }
                        else if(tag.equals("firstimage")){
                            buffer.append("이미지 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapx 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("  ,  "); //줄바꿈 문자 추가
                        }
                        else if(tag.equals("firstimage2")){
                            buffer.append("이미지 :");
                            xpp.next();
                            buffer.append(xpp.getText());//mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append("\n"); //줄바꿈 문자 추가
                        }

                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        buffer.append("파싱 끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData method....
}

