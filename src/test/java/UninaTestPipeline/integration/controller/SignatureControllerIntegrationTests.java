package UninaTestPipeline.integration.controller;


import controller.SignatureBuilder;
import controller.SignatureController;
import model.Signature;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class SignatureControllerIntegrationTests {

    private List<Signature> signatures;

    @Before
    public void setUp() {
        SignatureController sc = new SignatureController();
        sc.wipeSignatureFile();

        this.signatures = new ArrayList<>();
        this.signatures.add(new Signature("Pino", "Occhio"));
        this.signatures.add(new Signature("Topo", "Lino"));
    }

    @Test
    public void addSignatureResponseTest() {
        String body;
        SignatureController sc = new SignatureController();

        body = sc.addSignature(this.signatures.get(0));
        assertThat(body,equalTo("Hi " + this.signatures.get(0).getFirstName() + " " + this.signatures.get(0).getLastName() + ", we've just added you to the welcome list!"));

        body = sc.addSignature(this.signatures.get(1));
        assertThat(body,equalTo("Hi " + this.signatures.get(1).getFirstName() + " " + this.signatures.get(1).getLastName() + ", we've just added you to the welcome list!"));
    }

    @Test
    public void getSignaturesResponseTest() {
        String body, addBody;
        SignatureController sc = new SignatureController();

        body = sc.showSignatures();
        assertThat(body,equalTo("Welcome to: \n"));

        addBody = sc.addSignature(this.signatures.get(0));
        body = sc.showSignatures();
        assertThat(body,equalTo("Welcome to: \n" + this.signatures.get(0).getFirstName() + " " + this.signatures.get(0).getLastName()
                + "\n"));

        addBody = sc.addSignature(this.signatures.get(1));
        body = sc.showSignatures();
        assertThat(body,equalTo("Welcome to: \n" + this.signatures.get(0).getFirstName() + " " + this.signatures.get(0).getLastName()
                + "\n" + this.signatures.get(1).getFirstName() + " " + this.signatures.get(1).getLastName() + "\n"));

    }

    @Test
    public void FailureTest(){
        fail();
    }
}
