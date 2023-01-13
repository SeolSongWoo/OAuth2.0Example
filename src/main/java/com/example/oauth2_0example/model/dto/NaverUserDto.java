package com.example.oauth2_0example.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONObject;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NaverUserDto {

    private String mobile;
    private String name;
    private String id;
    private String email;
    private String mobile_e164;

    private String Type = "NAVER";


    public NaverUserDto(String userData) {
        JSONObject temp = new JSONObject(userData);
        JSONObject user = temp.getJSONObject("response");
        this.mobile = user.getString("mobile");
        this.name = user.getString("name");
        this.id = user.getString("id");
        this.email = user.getString("email");
        this.mobile_e164 = user.getString("mobile_e164");
    }

}
