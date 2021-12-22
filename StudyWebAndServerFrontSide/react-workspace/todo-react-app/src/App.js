import logo from './logo.svg';
import './App.css';
import React from 'react';

import Todo from './Todo';
//import {Paper, List, Container} from "@material-ui/core"

import AddTodo from "./AddTodo"
import { call, signout } from './service/ApiService';

import {
  Paper,
  List,
  Container,
  Grid,
  Button,
  AppBar,
  Toolbar,
  Typography,
} from "@material-ui/core"


class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      items : [
        //{id:0,title:"HIHI1",done:true},
        //{id:1,title:"HIHI3",done:false},
      ],
      loading: true,
    };
  }

 /* componentDidMount() {
    const requestOptions = {
      method: "GET",
      headers: {"Content-Thype" : "application/json"},
    };

    fetch("http://localhost:8080/todo",requestOptions)
    .then((response)=> response.json())
    .then(
      (response) => {
        this.setState({
          items:response.data,
        });
      },
      (error) => {
        this.setState({
          error,
        })
      }
    )
  }

  //추가 버튼 클릭시 메소드
  //추가 버튼은 AddTodo.js 에 있지만 추가 할건 여기서 Todo를 추가해야 함으로 여기서 정의
  add = (item) => {
    const thisItems = this.state.items;
    item.id = "ID-"  + thisItems.length;
    item.done = false;
    thisItems.push(item);
    this.setState({items:thisItems});
    console.log("items : ", this.state.items);
  }

  delete = (item) => {
    const thisItems = this.state.items;
    console.log("Before Update Items : ", this.state.items);
    const newItems = thisItems.filter(e =>e.id !== item.id);
    this.setState({items: newItems},()=>{
      console.log("Update Items : ", this.state.items);
    });
  }*/

  
  componentDidMount() {
    call("/todo","GET",null).then((response) => this.setState({items: response.data, loading:false}));
  }

  add = (item) => {
    call("/todo","POST",item).then((response) => this.setState({items: response.data}));
  }

  delete = (item) => {
    call("/todo","DELETE",item).then((response) => this.setState({items: response.data}));
  }

  update = (item) => {
    call("/todo","PUT",item).then((response) => this.setState({items: response.data}));
  }

  render(){
    //var todoItems = this.state.items.map((item, idx) => (<Todo item={item} key={item.id}/>))
    var todoItems = this.state.items.length > 0 && (
      <Paper style={{margin:16}}>
        <List>
          {this.state.items.map((item, idx) => (<Todo item={item} key={item.id} delete={this.delete} update={this.update}/>))}
        </List>
      </Paper>
    );

    var navigationBar = (
      <AppBar position="static">
        <Toolbar>
          <Grid justify="space-between" container>
            <Grid item>
              <Typography variant="h6">오늘의 할일</Typography>
            </Grid>
            <Grid>
              <Button color="inherit" onClick={signout}>
                로그아웃
              </Button>
            </Grid>
          </Grid>
        </Toolbar>
      </AppBar>
    );

    var todoListPage = (
      <div>
        {navigationBar}
        <Container maxWidth="md">
          <AddTodo add={this.add}/>
          <div className="TodoList">{todoItems}</div>
        </Container>
      </div>
    );

    var loadingPage = <h1> 로딩중... </h1>;

    var content = loadingPage;

    if(!this.state.loading) {
      content = todoListPage;
    }

    return <div className="App">{content}</div>


    /*return (
      <div className="App">
        {navigationBar}
        <Container maxWidth="md">
          <AddTodo add={this.add} />
          <div className="TodoList">{todoItems}</div>
        </Container>
      </div>
    );*/
  }
}

/*
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}*/

export default App;
