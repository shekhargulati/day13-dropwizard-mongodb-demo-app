package com.shekhar.reader30;

import net.vz.mongodb.jackson.JacksonDBCollection;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.shekhar.reader30.representations.Blog;
import com.shekhar.reader30.resources.BlogResource;
import com.shekhar.reader30.resources.IndexResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;

public class Reader30Service extends Service<Reader30Configuration> {

    public static void main(String[] args) throws Exception {
        new Reader30Service().run(new String[] { "server", "src/main/resources/config.yml" });
    }

    @Override
    public void initialize(Bootstrap<Reader30Configuration> bootstrap) {
        bootstrap.setName("reader30");
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(Reader30Configuration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        DB db = mongo.getDB(configuration.mongodb);
        
        JacksonDBCollection<Blog, String> blogs = JacksonDBCollection.wrap(db.getCollection("blogs"), Blog.class, String.class);
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.manage(mongoManaged);
        
        environment.addHealthCheck(new MongoHealthCheck(mongo));
        
        environment.addResource(new BlogResource(blogs));
        environment.addResource(new IndexResource(blogs));
    }

}
