import React from "react";
import {
    ListItem, 
    ListItemText, 
    InputBase, 
    Checkbox, 
    ListItemSecondaryAction, 
    IconButton} from "@material-ui/core";
import DeleteOutLined from "@material-ui/icons/DeleteOutlined"

class Todo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {item : props.item, readOnly:true};
        this.delete = props.delete;
    }

    deleteEventHandler = () => {
        this.delete(this.state.item);
    }

    //readOnly를 flag로 수정 가능 불가능 조절
    offReadOnlyMdoe = () => {
        console.log("Event!",this.state.readOnly)
        this.setState({readOnly:false},()=>{
            console.log("ReadOnly? ",this.state.readOnly)
        });
    }

    //Enter 치면 읽기전용으로 변경
    enterKeyEventHandler = (e) => {
        if(e.key == 'Enter') {
            this.setState({readOnly:true});
        }
    }

    //입력 발생시 입력을 읽어 글자 변경
    editEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({item : thisItem});
    }

    //Check 박스 동작 
    checkboxEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.done = !thisItem.done;
        this.setState({item : thisItem});
    }
    
    render(){
        const item = this.state.item;
        return (
            <ListItem>
                <Checkbox checked={item.done} onChange={this.checkboxEventHandler}/>
                <ListItemText>
                    <InputBase 
                        inputProps={{"aria-label":"naked", readOnly: this.state.readOnly,}}
                        type="text"
                        id={item.id}
                        name={item.id}
                        value={item.title}
                        multiline={true}
                        fullWidth={true}
                        onClick={this.offReadOnlyMdoe}
                        onKeyPress={this.enterKeyEventHandler}
                        onChange={this.editEventHandler}
                    />
                </ListItemText>
                <ListItemSecondaryAction>
                    <IconButton aria-label="Delete Todo" onClick={this.deleteEventHandler}>
                        <DeleteOutLined />
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        );
    }
}

export default Todo;