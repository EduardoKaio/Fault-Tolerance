package br.com.toecommerce.demo.Models;

import org.springframework.stereotype.Component;

@Component
public class FaultTolerance {
    private boolean faultTolerance;

    public boolean isFaultTolerance() {
        return faultTolerance;
    }

    public void setFaultTolerance(boolean faultTolerance) {
        this.faultTolerance = faultTolerance;
    }
}
