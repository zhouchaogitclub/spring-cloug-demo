package com.zc.entities;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author zhouchao
 * @date 2019/5/25 12:28
 */
@Getter
@Setter
@ToString
public class Dept implements Serializable {

  /**
   * 主键
   */
  private Long deptNo;
  /**
   * 部门名称
   */
  private String dName;
  /**
   * 来自哪个数据库
   */
  private String dbSource;
}
