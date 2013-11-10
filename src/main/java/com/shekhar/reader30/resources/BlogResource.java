package com.shekhar.reader30.resources;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

import com.shekhar.reader30.representations.Blog;
import com.yammer.metrics.annotation.Timed;

@Path("/blogs")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class BlogResource {

    private JacksonDBCollection<Blog, String> collection;

    public BlogResource(JacksonDBCollection<Blog, String> blogs) {
        this.collection = blogs;
    }

    @GET
    @Timed
    public List<Blog> blogsTimeline() {
        DBCursor<Blog> dbCursor = collection.find();
        List<Blog> blogs = new ArrayList<>();
        while (dbCursor.hasNext()) {
            Blog blog = dbCursor.next();
            blogs.add(blog);
        }
        return blogs;

    }

    @POST
    @Timed
    public Response publishNewBlog(@Valid Blog blog) {
        collection.insert(blog);
        return Response.noContent().build();
    }
}
