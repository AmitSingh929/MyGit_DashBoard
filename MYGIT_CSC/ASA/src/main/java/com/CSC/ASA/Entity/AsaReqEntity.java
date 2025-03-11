package com.CSC.ASA.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "asa_req_00")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsaReqEntity {
    @Id
    private String _id;
    private long sno;
    @Field("client_id")
    private String clientId;
    private String txn;
    private int txnKey;
    private String ac;
    private String sa;
    private String ver;
    private String uses;
    @Field("auth_mode")
    private String authMode;
    private String de;
    @Field("creation_date")
    private LocalDateTime creationDate;
    @Field("aua_ts")
    private LocalDateTime auaTs;
    @Field("client_ip")
    private String clientIp;
    @Field("ip_addr")
    private String ipAddr;
    private int flag;
}
