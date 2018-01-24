const React = require('react');
const ReactDOM = require('react-dom');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {bathroomTemp: null, bedRoomTemp: null, balconyTemp: null};
    }

    componentDidMount() {
        fetch('/temperature/BATHROOM').then(res => res.json()).then(response => {
            this.setState({bathroomTemp: response});
        });

        fetch('/temperature/BEDROOM').then(res => res.json()).then(response => {
            this.setState({bedRoomTemp: response});
        });

        fetch('/temperature/BALCONY').then(res => res.json()).then(response => {
            this.setState({balconyTemp: response});
        });
    }

    render() {
        return (
            <div>
                <Temperature room="Łazienka" temperature={this.state.bathroomTemp}/>
                <Temperature room="Sypialnia" temperature={this.state.bedRoomTemp}/>
                <Temperature room="Balkon" temperature={this.state.balconyTemp}/>
            </div>
        )
    }
}

class Temperature extends React.Component {
    render() {
        return (
            <div>
                {this.props.room} : {this.props.temperature}
            </div>
        )
    }
}

ReactDOM.render(<App/>, document.getElementById('react'));
