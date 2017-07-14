package sajt.dzfppt.entity;

import java.util.Date;

public class Dsptxx {
    private String id;

    private String dsptbm;

    private Date zcDate;

    private String state;

    private String zcm;

    private String sqm;

    private String sjly;

    private String isTz;

    private String tzUrl;

    private String publicKey;

    private String _3desKey;

    private byte[] caFile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDsptbm() {
        return dsptbm;
    }

    public void setDsptbm(String dsptbm) {
        this.dsptbm = dsptbm == null ? null : dsptbm.trim();
    }

    public Date getZcDate() {
        return zcDate;
    }

    public void setZcDate(Date zcDate) {
        this.zcDate = zcDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getZcm() {
        return zcm;
    }

    public void setZcm(String zcm) {
        this.zcm = zcm == null ? null : zcm.trim();
    }

    public String getSqm() {
        return sqm;
    }

    public void setSqm(String sqm) {
        this.sqm = sqm == null ? null : sqm.trim();
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly == null ? null : sjly.trim();
    }

    public String getIsTz() {
        return isTz;
    }

    public void setIsTz(String isTz) {
        this.isTz = isTz == null ? null : isTz.trim();
    }

    public String getTzUrl() {
        return tzUrl;
    }

    public void setTzUrl(String tzUrl) {
        this.tzUrl = tzUrl == null ? null : tzUrl.trim();
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }

    public String get3desKey() {
        return _3desKey;
    }

    public void set3desKey(String _3desKey) {
        this._3desKey = _3desKey == null ? null : _3desKey.trim();
    }

    public byte[] getCaFile() {
        return caFile;
    }

    public void setCaFile(byte[] caFile) {
        this.caFile = caFile;
    }
}