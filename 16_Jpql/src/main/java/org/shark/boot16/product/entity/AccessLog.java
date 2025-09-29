package org.shark.boot16.product.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "access_logs")

@Setter
@Getter
public class AccessLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "log_id", nullable = false)
  private Integer logId;
  
  @Column(length = 30, name = "log_type", nullable = false)
  private String logType;
  
  @Column(length = 50, name = "log_message", nullable = false)
  private String logMessage;
  
  @Column(length = 20, name = "log_level", nullable = false)
  private String logLevel;
  
  @Column(length = 45, name = "client_ip")
  private String clientIp;
  
  @Column(length = 200, name = "user_agent")
  private String userAgent;
  
  @Column(name = "create_date", columnDefinition = "datetime")
  private LocalDateTime createDate;
  
  protected AccessLog() {}
  
  public static AccessLog createAccessLog(String logType, String logMessage, String logLevel, String clientIp, String userAgent) {
    AccessLog accessLog = new AccessLog();
    accessLog.logType = logType;
    accessLog.logMessage = logMessage;
    accessLog.logLevel = logLevel;
    accessLog.clientIp = clientIp;
    accessLog.userAgent = userAgent;
    return accessLog;
  }

  @Override
  public String toString() {
    return "AccessLog [id=" + logId + ", logType=" + logType + ", logMessage=" + logMessage + ", logLevel=" + logLevel
        + ", clientIp=" + clientIp + ", userAgent=" + userAgent + ", createDate=" + createDate + "]";
  }
  
  
}
