import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Select from 'react-select';

class VegetableSelect extends Component {
    constructor(props) {
        super(props);

        this.state = {
        }
    }
   
    mapVegetablesForSelect() {
        return this.props.vegetables.map(({ id, name }) => ({ value: id, label: name }));
    }

    render() {
        return (
            <Select
                options={this.mapVegetablesForSelect()}
                onChange={({ value }) => this.props.onChange(value)}
                placeholder="Wybierz warzywo"
                noOptionsMessage={() => 'Brak dostępnych warzyw dla linii'}
            />
        );
    }

}

VegetableSelect.propTypes = {
    vegetables: PropTypes.array.isRequired,
}

export default VegetableSelect;