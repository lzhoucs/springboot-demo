package com.example.springbootdemo;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @PostMapping(value = "/helloworld", produces = "application/json", consumes = "application/vnd.v1+json")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = HelloDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad name",
            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<?> hellov1(@RequestBody RequestV1 request)
    {
        final String name = request.getNameV1();
        if ("error".equalsIgnoreCase(name))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("invalid name: " + name));
        }
        return ResponseEntity.ok(new HelloDTO("Greetings from Spring Boot v1! " + name));
    }

    @PostMapping(value = "/helloworld", produces = "application/json", consumes = "application/vnd.v2+json")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = HelloDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad name",
            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<?> hellov2(@RequestBody RequestV2 request)
    {
        final String name = request.getNameV2();
        if ("error".equalsIgnoreCase(name))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("invalid name: " + name));
        }
        return ResponseEntity.ok(new HelloDTO("Greetings from Spring Boot v2! " + name));
    }

    class HelloDTO
    {
        private String message;

        public HelloDTO(String message)
        {
            this.message = message;
        }

        public String getMessage()
        {
            return message;
        }
    }

    class ErrorDTO
    {
        private String errorMessage;

        public ErrorDTO(String errorMessage)
        {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage()
        {
            return errorMessage;
        }
    }

    static class RequestV1 {
        private String nameV1;

        public RequestV1()
        {
        }

        public String getNameV1()
        {
            return nameV1;
        }

        public void setNameV1(String nameV1)
        {
            this.nameV1 = nameV1;
        }
    }

    static class RequestV2 {
        private String nameV2;

        public RequestV2()
        {
        }

        public String getNameV2()
        {
            return nameV2;
        }

        public void setNameV2(String nameV2)
        {
            this.nameV2 = nameV2;
        }
    }
}
