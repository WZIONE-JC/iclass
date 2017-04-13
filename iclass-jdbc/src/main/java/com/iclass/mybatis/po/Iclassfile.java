package com.iclass.mybatis.po;

import com.iclass.user.component.utils.IclassUtil;

import java.io.Serializable;

public class Iclassfile implements Serializable{
    private Integer fileid;

    private String filecode;

    private String filename;

    private String filepath;

    private String filerelativepath;

    private String filedesc;

    private String filecreator;

    private String filecreatetime;

    private Integer filetype;

    private Integer filedownloadtime;

    private Integer filestatus;

    public Iclassfile(Integer fileid, String filecode, String filename, String filepath, String filerelativepath, String filedesc, String filecreator, String filecreatetime, Integer filetype, Integer filedownloadtime, Integer filestatus) {
        this.fileid = fileid;
        this.filecode = filecode;
        this.filename = filename;
        this.filepath = filepath;
        this.filerelativepath = filerelativepath;
        this.filedesc = filedesc;
        this.filecreator = filecreator;
        this.filecreatetime = filecreatetime;
        this.filetype = filetype;
        this.filedownloadtime = filedownloadtime;
        this.filestatus = filestatus;
    }

    public Iclassfile() {
        super();
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilecode() {
        return filecode;
    }

    public void setFilecode(String filecode) {
        this.filecode = filecode;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getFilerelativepath() {
        return filerelativepath;
    }

    public void setFilerelativepath(String filerelativepath) {
        this.filerelativepath = filerelativepath;
    }

    public String getFiledesc() {
        return filedesc;
    }

    public void setFiledesc(String filedesc) {
        this.filedesc = filedesc;
    }

    public String getFilecreator() {
        return filecreator;
    }

    public void setFilecreator(String filecreator) {
        this.filecreator = filecreator;
    }

    public String getFilecreatetime() {
        return filecreatetime;
    }

    public void setFilecreatetime(String filecreatetime) {
        this.filecreatetime = IclassUtil.formatTime(filecreatetime);
    }

    public Integer getFiletype() {
        return filetype;
    }

    public void setFiletype(Integer filetype) {
        this.filetype = filetype;
    }

    public Integer getFiledownloadtime() {
        return filedownloadtime;
    }

    public void setFiledownloadtime(Integer filedownloadtime) {
        this.filedownloadtime = filedownloadtime;
    }

    public Integer getFilestatus() {
        return filestatus;
    }

    public void setFilestatus(Integer filestatus) {
        this.filestatus = filestatus;
    }

    @Override
    public String toString() {
        return "Iclassfile{" +
                "fileid=" + fileid +
                ", filecode='" + filecode + '\'' +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", filerelativepath='" + filerelativepath + '\'' +
                ", filedesc='" + filedesc + '\'' +
                ", filecreator='" + filecreator + '\'' +
                ", filecreatetime='" + filecreatetime + '\'' +
                ", filetype=" + filetype +
                ", filedownloadtime=" + filedownloadtime +
                ", filestatus=" + filestatus +
                '}';
    }
}