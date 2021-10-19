import React from "react";
import {signin} from "./service/ApiService"
import { Button } from "@material-ui/core";
import { TextField } from "@material-ui/core";
import { Grid } from "@material-ui/core";
import { Typography } from "@material-ui/core";
import { Container } from "@material-ui/core";

class Login extends React.Component {
    constructor(props){
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        const email = data.get("email");
        const password = data.get("password");
        signin({email:email, password:password});
    }

    render(){
        return(
            <Container component="main" maxWidth="xs" style={{marginTop:"8%"}}>
                <Grid container spacing={2}>
                    <Grid item xs={12}>
                        <Typography component="h1" variant="h5">
                            로그인
                        </Typography>
                    </Grid>
                </Grid>
                <form noValidate onSubmit={this.handleSubmit}>
                    {" "}
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField variant="outlined" required fullWidth id="email"
                                 label="이메일주소" name="email" autoComplete="email"/>
                        </Grid>
                        <Grid item xs={12}>
                            <TextField variant="outlined" required fullWidth id="password"
                                 label="패스워드" name="password" autoComplete="current-password"/>
                        </Grid>
                        <Grid item xs={12}>
                            <Button type="submit" fullWidth variant="contained" color="primary">
                                로그인
                            </Button>
                        </Grid>
                    </Grid>
                </form>
            </Container>
        );
        // <p>로그인 페이지</p>
    }
}

export default Login;