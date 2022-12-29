package com.hq2808.blog.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)	
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 3712848328289479407L;
	
	@Column(name = "CREATED_AT", updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;
	
	@Column(name = "IS_DELETE")
	private Boolean isDelete;
	
	@Column(name = "CREATED_BY")
	private Long createdBy;
	
	@PrePersist
	public void prePersist() {
		isDelete = false;
		createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = LocalDateTime.now();
	}
}
