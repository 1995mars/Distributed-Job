package org.mars.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "SD_GOI_DADV")
@Data
public class SdGoiDadv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOI_ID")
    private Long goiId;

    @Column(name = "KYHOADON")
    private Long kyhoadon;

    @Column(name = "NHOMTB_ID")
    private Long nhomtbId;

    @Column(name = "MA_TB")
    private String maTb;

    @Column(name = "TEN_TB")
    private String tenTb;

    @Column(name = "DIACHI_TB")
    private String diachiTb;

    @Column(name = "TRANGTHAI")
    private Integer trangthai;

    @Column(name = "LOAITB_ID")
    private Integer loaitbId;

    @Column(name = "NGAY_DK")
    private Date ngayDk;

    @Column(name = "DICHVUVT_ID")
    private Integer dichvuvtId;

    @Column(name = "TIEN")
    private Integer tien;

    @Column(name = "MA_GOI")
    private String maGoi;

    @Column(name = "THUEBAO_ID")
    private Long thuebao;

    @Column(name = "VAT")
    private Long vat;

    // Getters & setters
}
