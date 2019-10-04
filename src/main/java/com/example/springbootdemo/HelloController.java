package com.example.springbootdemo;

import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping(value = "/hello/{name}", produces = "application/json")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = HelloDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad name",
            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<?> hello(@PathVariable String name)
    {
        if ("error".equalsIgnoreCase(name))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("invalid name: " + name));
        }
        return ResponseEntity.ok(new HelloDTO("Greetings from Spring Boot! " + name));
    }

    class HelloDTO
    {
        private String message;

        public HelloDTO(String message)
        {
            this.message = message;
        }

        public HelloDTO()
        {
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

        public ErrorDTO()
        {
        }

        public String getErrorMessage()
        {
            return errorMessage;
        }
    }
}
