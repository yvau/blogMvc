package com.blog.mvc.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(
			name="categoryGen",
			table="hibernate_sequences",
			pkColumnName="sequence_name",
			valueColumnName="next_val",
			pkColumnValue="categoryGenId",
			allocationSize=1)

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="categoryGen")
	private Integer id;

	private String name;

	@Column(name="post_count")
	private String postCount;

	private String slug;

	//bi-directional many-to-one association to Post
	@OneToMany(mappedBy="category")
	private List<Post> posts;

	public Category() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostCount() {
		return this.postCount;
	}

	public void setPostCount(String postCount) {
		this.postCount = postCount;
	}

	public String getSlug() {
		return this.slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setCategory(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setCategory(null);

		return post;
	}

}