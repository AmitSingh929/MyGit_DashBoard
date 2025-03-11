package com.CSC.ASA.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "asa_res_00")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsaResEntity {
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
    private String ko;
    @Field("kyc_status")
    private String kycStatus;
    @Field("uid_type")
    private String uidType;
    @Field("auth_mode")
    private String authMode;
    @Field("creation_date")
    private LocalDateTime creationDate;
    @Field("res_time")
    private int resTime;
    private int flag;
}