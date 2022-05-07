package com.sulay.photoapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "album_id")
    @JsonIgnore
    private Album album;

    @OneToMany(mappedBy = "photo")
    private List<Comment> comments;

    public void addComment(Comment comment) {
        if (this.comments.isEmpty()) {
            this.comments = new ArrayList<>();
        }
        this.comments.add(comment);
    }


}
