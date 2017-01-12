package com.blog.mvc.model;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the comments database table.
 */
@Entity
@Table(name = "comments")
@NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c")
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableGenerator(
            name="commentGen",
            table="hibernate_sequences",
            pkColumnName="sequence_name",
            valueColumnName="next_val",
            pkColumnValue="commentGenId",
            allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="commentGen")
    private Integer id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String content;

    private Timestamp created;

    private String mail;

    private String username;

    //bi-directional many-to-one association to Post
    @ManyToOne
    private Post post;

    public Comment() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Post getPost() {
        return this.post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}