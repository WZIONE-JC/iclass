package com.iclass.mybatis.po;

public class Classcourseattendtime {
    private Integer classcourseattendtimeid;

    private Integer classcourseid;

    private Integer attendnumber;

    private Integer attendtime;

    public Classcourseattendtime(Integer classcourseattendtimeid, Integer classcourseid, Integer attendnumber, Integer attendtime) {
        this.classcourseattendtimeid = classcourseattendtimeid;
        this.classcourseid = classcourseid;
        this.attendnumber = attendnumber;
        this.attendtime = attendtime;
    }

    public Classcourseattendtime() {
        super();
    }

    public Integer getClasscourseattendtimeid() {
        return classcourseattendtimeid;
    }

    public void setClasscourseattendtimeid(Integer classcourseattendtimeid) {
        this.classcourseattendtimeid = classcourseattendtimeid;
    }

    public Integer getClasscourseid() {
        return classcourseid;
    }

    public void setClasscourseid(Integer classcourseid) {
        this.classcourseid = classcourseid;
    }

    public Integer getAttendnumber() {
        return attendnumber;
    }

    public void setAttendnumber(Integer attendnumber) {
        this.attendnumber = attendnumber;
    }

    public Integer getAttendtime() {
        return attendtime;
    }

    public void setAttendtime(Integer attendtime) {
        this.attendtime = attendtime;
    }

    @Override
    public String toString() {
        return "Classcourseattendtime{" +
                "classcourseattendtimeid=" + classcourseattendtimeid +
                ", classcourseid=" + classcourseid +
                ", attendnumber=" + attendnumber +
                ", attendtime='" + attendtime + '\'' +
                '}';
    }
}