import { useState, useEffect } from 'react';

const AddNote = ({ handleAddNote }) => {
	const [note, setNote] = useState({
		id: null,
		content: ""
	});
	const characterLimit = 201;



	const handleChange = (event) => {
		if (characterLimit - event.target.value.length >= 0) {
			updateNote("content", event.target.value);
		}
	};

	const handleSaveClick = () => {
		if (note.content.trim().length > 0) {
			handleAddNote(note.content);
			updateNote("content", '');
			window.location.href = `/`;
		}
	};

	function updateNote(prop, value) {
		const newNote = { ...note };
		newNote[prop] = value;
		setNote(newNote);
	}

	return (
		<div className='note new'>
			<textarea
				rows='8'
				cols='10'
				placeholder='Type to add a note...'
				value={note.content}
				onChange={handleChange}
			></textarea>
			<div className='note-footer'>
				<small>
					{characterLimit - note.content.length} Remaining
				</small>
				<button className='save' onClick={handleSaveClick}>
					Save
				</button>
			</div>
		</div>
	);
};

export default AddNote;