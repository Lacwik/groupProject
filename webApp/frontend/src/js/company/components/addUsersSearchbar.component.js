import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Paper from '@material-ui/core/Paper';
import InputBase from '@material-ui/core/InputBase';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';

class AddUsersSearchbar extends Component {
    constructor(props) {
        super(props);

        this.state = {
            value: '',
        }
    }

    onChange = e => {
        const { value } = e.target;
        this.setState(state => ({ ...state, value }));
    }

    onClick = () => {
        this.props.onSearch(this.state.value);
    }

    render() {
        return ( 
            <Paper elevation={1} className="add-user-search-bar">
                <InputBase placeholder="Wyszukaj użytkownika" className="add-user-search-bar__input" onChange={this.onChange} />
                <IconButton aria-label="Szukaj" className="add-user-search-bar__button" onClick={this.onClick}>
                    <SearchIcon />
                </IconButton>
            </Paper>
        );
    }
}

AddUsersSearchbar.propTypes = {
    onSearch: PropTypes.func.isRequired,
}

export default AddUsersSearchbar;