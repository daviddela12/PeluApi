package com.daviddela.peluapi.exception;

import com.daviddela.peluapi.common.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundException extends BaseRuntimeException {

    public ResourceNotFoundException( Long id ) {
        super("Resource with identificator " + id + " not found", HttpStatus.NOT_FOUND);
    }
}
