package com.authservis.AuthorizationService.ll_user.controller;

import com.authservis.AuthorizationService.ll_user.service.LLUserService;
import com.authservis.AuthorizationService.ll_user.models.LLUser;
import com.authservis.AuthorizationService.utils.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Все энддпоинты, связанные с пользователем
 */
@RestController
@RequestMapping(path = "api/v1/ll_users")
public class LLUserController {

    private final LLUserService LLUserService;

    @Autowired
    public LLUserController(LLUserService LLUserService) {
        this.LLUserService = LLUserService;
    }

    @Operation(summary = "Получить пользователя по его id")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200", description = "Ok",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404", description = "User not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LLUser.class)
                    )
            )
        }
    )
    @GetMapping(path = "/getUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        Optional<LLUser> user = LLUserService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            ApiResponse response = new ApiResponse(
                    "User not found",
                    HttpStatus.NOT_FOUND,
                    HttpStatus.NOT_FOUND.value()
            );
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201", description = "Created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            )
        }
    )
    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody LLUser user,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        LLUserService.registerUser(user);
        ApiResponse response = new ApiResponse(
                "Success",
                HttpStatus.CREATED,
                HttpStatus.CREATED.value()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Проверка работоспособности сервера")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "pong"
            )
        }
    )
    @GetMapping(path = "/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(
                        "pong",
                        HttpStatus.OK,
                        HttpStatus.OK.value()
                )
        );
    }

}
