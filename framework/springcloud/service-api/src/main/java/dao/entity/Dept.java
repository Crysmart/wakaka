package dao.entity;

import java.io.Serializable;

/**
 * @author Crysmart
 * @date 2020/8/17 16:48
 */
public class Dept implements Serializable {
    private Integer id;
    private String deptName;
    private String deptManager;
    private String microName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptManager() {
        return deptManager;
    }

    public void setDeptManager(String deptManager) {
        this.deptManager = deptManager;
    }

    public String getMicroName() {
        return microName;
    }

    public void setMicroName(String microName) {
        this.microName = microName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", deptManager='" + deptManager + '\'' +
                ", microName='" + microName + '\'' +
                '}';
    }
}
