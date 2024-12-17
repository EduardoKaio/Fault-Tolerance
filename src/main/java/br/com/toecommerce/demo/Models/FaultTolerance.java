package br.com.toecommerce.demo.Models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class FaultTolerance {
    private boolean faultTolerance;

    public boolean isFaultTolerance() {
        return faultTolerance;
    }

    public void setFaultTolerance(boolean faultTolerance) {
        this.faultTolerance = faultTolerance;
    }
}
