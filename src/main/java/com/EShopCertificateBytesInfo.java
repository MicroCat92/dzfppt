package com;

/**
 * Created by Martin.Ou on 2014/11/20.
 */
public final class EShopCertificateBytesInfo {
    private String taxpayerIdentifyNo;
    private byte[] trustsBytes;
    private byte[] privatePFXBytes;
    private byte[] publicPFXBytes;
    private String privatePFXKey;

    public String getTaxpayerIdentifyNo() {
        return taxpayerIdentifyNo;
    }

    public void setTaxpayerIdentifyNo(String taxpayerIdentifyNo) {
        this.taxpayerIdentifyNo = taxpayerIdentifyNo;
    }

    public byte[] getTrustsBytes() {
        return trustsBytes;
    }

    public void setTrustsBytes(byte[] trustsBytes) {
        this.trustsBytes = trustsBytes;
    }

    public byte[] getPrivatePFXBytes() {
        return privatePFXBytes;
    }

    public void setPrivatePFXBytes(byte[] privatePFXBytes) {
        this.privatePFXBytes = privatePFXBytes;
    }

    public byte[] getPublicPFXBytes() {
        return publicPFXBytes;
    }

    public void setPublicPFXBytes(byte[] publicPFXBytes) {
        this.publicPFXBytes = publicPFXBytes;
    }

    public String getPrivatePFXKey() {
        return privatePFXKey;
    }

    public void setPrivatePFXKey(String privatePFXKey) {
        this.privatePFXKey = privatePFXKey;
    }
}
