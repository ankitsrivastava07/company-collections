FROM public.ecr.aws/lambda/java:11

# copy the built Spring Boot fat jar (adjust if your artifact name is different)
COPY target/*.jar ${LAMBDA_TASK_ROOT}/app.jar

# tell Spring Cloud Function which bean to invoke by default
ENV SPRING_CLOUD_FUNCTION_DEFINITION=companyList

# Lambda handler for Spring Cloud Function
CMD ["org.springframework.cloud.function.adapter.aws.FunctionInvoker::handleRequest"]
