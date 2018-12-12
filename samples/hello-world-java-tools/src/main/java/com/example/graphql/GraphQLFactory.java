/*
 * Copyright 2017-2018 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.graphql;

import com.coxautodev.graphql.tools.SchemaParser;
import com.coxautodev.graphql.tools.SchemaParserBuilder;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

/**
 * @author Marcel Overdijk
 */
@Factory
public class GraphQLFactory {

    /**
     * Creates the {@link GraphQL} bean.
     *
     * @return the GraphQL bean.
     */
    @Bean
    @Singleton
    public GraphQL graphQL(HelloQueryResolver helloQueryResolver) {

        final SchemaParserBuilder builder = SchemaParser.newParser()
                .file("schema.graphqls")
                .resolvers(helloQueryResolver);

        GraphQLSchema graphQLSchema = builder.build().makeExecutableSchema();

        return GraphQL.newGraphQL(graphQLSchema).build();
    }
}
