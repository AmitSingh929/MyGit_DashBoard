package com.CSC.AUA.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "aua_req_00")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuaReqEntity {
    @Id
    private String _id;
    private long sno;
    @Field("client_id")
    private String clientId;
    private String txn;
    private int txnKey;
    private String rc;
    private String ac;
    private String sa;
    private String ver;
    private String uses;
    @Field("rds_data")
    private String rdsData;
    private String dc;
    private String mi;
    @Field("auth_mode")
    private String authMode;
    private String otp;
    @Field("uid_type")
    private String uidType;
    @Field("creation_date")
    private LocalDateTime creationDate;
    @Field("client_ts")
    private LocalDateTime clientTs;
    @Field("ip_addr")
    private String ipAddr;
    private int flag;
}
