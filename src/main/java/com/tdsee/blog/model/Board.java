package com.tdsee.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 - <html> 태그 섞여서 디자인됨

	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One // 하나의 유저는 여러 개의 게시글을 쓸 수 있다
	@JoinColumn(name = "userId")
	private User user; // DB는 오브젝트를 저장할 수 없음 // FK, 자바는 오브젝트 저장 가능
	
	@OneToMany(mappedBy="board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy => 연관관게의 주인이 아니다(나는 FK가 아니니 DB에 컬럼을 만들지 x)
	@JsonIgnoreProperties({"board"}) // 무한 참조 방지 
	@OrderBy("id desc")
	//JoinColumn (FK) 필요 없음
	private List<Reply> replies;
	
	@CreationTimestamp
	private Timestamp createDate;
}
