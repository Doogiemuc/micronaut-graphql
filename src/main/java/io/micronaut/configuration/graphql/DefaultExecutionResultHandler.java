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

package io.micronaut.configuration.graphql;

import graphql.ExecutionResult;
import io.micronaut.core.async.publisher.Publishers;
import io.micronaut.http.HttpResponse;
import org.reactivestreams.Publisher;

/**
 * The default GraphQL execution result handler.
 *
 * @author Marcel Overdijk
 * @since 1.0
 */
public class DefaultExecutionResultHandler implements ExecutionResultHandler {

    @Override
    public Publisher<HttpResponse<GraphQLResponseBody>> handleExecutionResult(Publisher<ExecutionResult> executionResultPublisher) {
        return Publishers.map(executionResultPublisher, executionResult -> {
            GraphQLResponseBody body = new GraphQLResponseBody(executionResult.toSpecification());
            return HttpResponse.ok(body);
        });
    }
}
