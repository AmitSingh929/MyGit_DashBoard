package com.CSC.AUA.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "aua_res_00")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuaResEntity {
    @Id
    private String _id;
    private long sno;
    @Field("client_id")
    private String clientId;
    private String txn;
    private int txnKey;
    private String ret;
    private String code;
    private LocalDateTime ts;
    private String err;
    private String actn;
    @Field("uid_token")
    private String uidToken;
    @Field("auth_mode")
    private String authMode;
    @Field("creation_date")
    private LocalDateTime creationDate;
    @Field("asa_ts")
    private LocalDateTime asaTs;
    @Field("asa_ip")
    private String asaIp;
    private String info;
    @Field("res_time")
    private Integer resTime;
    private Integer flag;
}
