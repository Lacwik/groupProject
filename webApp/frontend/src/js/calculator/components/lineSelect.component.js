import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Select from 'react-select';

class LineSelect extends Component {
    mapLinesToSelect() {
        return this.props.lines.map(({ id, name }) => ({ value: id, label: name }));
    }

    render() {
        return (
            <Select
                options={this.mapLinesToSelect()}
                onChange={({ value }) => this.props.onChange(value)}
                placeholder="Wybierz linię"
                noOptionsMessage={() => 'Brak dostępnych linii'}
            />
        );
    }

}

LineSelect.propTypes = {
    lines: PropTypes.array.isRequired,
    onChange: PropTypes.func.isRequired,
};

export default LineSelect;