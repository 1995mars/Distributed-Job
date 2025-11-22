package org.mars.dto;

public class TongTienVat {
    private double tongTien;
    private double tongVat;

    public TongTienVat(double tongTien, double tongVat) {
        this.tongTien = tongTien;
        this.tongVat = tongVat;
    }

    public void add(double tien, double vat) {
        this.tongTien += tien;
        this.tongVat += vat;
    }

    public double getTongTien() { return tongTien; }
    public double getTongVat() { return tongVat; }
}

