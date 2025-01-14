package com.hcc.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum AuthorityEnum {

    ROLE_LEANER(1, "Role Learner"),
    ROLE_REVIEWER(2, "Role Reviewer"),
    ROLE_ADMIN(3, "Role Admin");

    private int authorityNumber;
    private String authorityName;

    AuthorityEnum(int authorityNumber, String authorityName) {
        this.authorityNumber = authorityNumber;
        this.authorityName = authorityName;
    }

    int getAuthorityNumber() {
        return authorityNumber;
    }

    String getAuthorityName() {
        return authorityName;
    }

}
